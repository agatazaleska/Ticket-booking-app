package com.example.demo.service.reservation;

import com.example.demo.dao.ReservationRepository;
import com.example.demo.entity.*;
import com.example.demo.exception.InvalidReservationException;
import com.example.demo.service.reservation_detail.ReservationDetailService;
import com.example.demo.service.room.RoomService;
import com.example.demo.service.screening.ScreeningService;
import com.example.demo.service.seat.SeatService;
import com.example.demo.service.seat_availability.SeatAvailabilityService;
import com.example.demo.service.ticket_price.TicketPriceService;
import com.example.demo.util.reservation_request.ReservationRequest;
import com.example.demo.util.RoomLayout;
import com.example.demo.util.reservation_request.TicketRequest;
import com.example.demo.util.reservation_request.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static int expirationMinutesBeforeScreening = 10;
    private ReservationRepository reservationRepository;
    private ScreeningService screeningService;
    private SeatAvailabilityService seatAvailabilityService;
    private SeatService seatService;
    private TicketPriceService ticketPriceService;
    private RoomService roomService;
    private ReservationDetailService reservationDetailService;

    @Autowired
    public ReservationServiceImpl(
            ReservationRepository theReservationRepository,
            ScreeningService theScreeningService,
            SeatAvailabilityService theSeatAvailabilityService,
            TicketPriceService theTicketPriceService,
            SeatService theSeatService,
            RoomService theRoomService,
            ReservationDetailService theReservationDetailService) {
        reservationRepository = theReservationRepository;
        screeningService = theScreeningService;
        seatAvailabilityService = theSeatAvailabilityService;
        ticketPriceService = theTicketPriceService;
        seatService = theSeatService;
        roomService = theRoomService;
        reservationDetailService = theReservationDetailService;
    }

    @Override
    public Reservation postReservation(ReservationRequest reservationRequest) {
        List<Seat> wantedSeats = getRequestedSeats(reservationRequest);
        Reservation reservation = processReservationRequest(reservationRequest);

        for (Seat seat : wantedSeats) {
            ReservationDetail detail = new ReservationDetail(
                    reservation.getId(), seat.getId());
            SeatAvailability availability = new SeatAvailability(
                    reservation.getScreeningId(), seat.getId(), false);

            seatAvailabilityService.save(availability);
            reservationDetailService.save(detail);
        }
        return reservationRepository.save(reservation);
    }

    private Reservation processReservationRequest(ReservationRequest reservationRequest) {
        checkIfIsEarlyEnough(reservationRequest);
        checkCustomerDataCorrectness(reservationRequest);
        checkForIsolatedSeat(reservationRequest);

        int requiredScreeningId = reservationRequest.getScreeningId();
        Screening requiredScreening = screeningService.findById(requiredScreeningId);
        Map<TicketType, Float> ticketPrices = ticketPriceService.getTicketPrices();
        return new Reservation(
            requiredScreeningId,
            reservationRequest.getCustomerData().getName(),
            reservationRequest.getCustomerData().getSurname(),
            reservationRequest.getCost(ticketPrices),
                requiredScreening.getDate().atTime(requiredScreening.getStartTime()).
                    minusMinutes(expirationMinutesBeforeScreening)
        );
    }

    private List<Seat> getRequestedSeats(ReservationRequest reservationRequest) {
        List<Seat> result = new ArrayList<>();

        int screeningId = reservationRequest.getScreeningId();
        Screening screening = screeningService.findById(screeningId);
        int roomNumber = screening.getRoomNumber();

        for (TicketRequest ticketRequest : reservationRequest.getRequestedTickets()) {
            Optional<Seat> maybeSeat = seatService.findByLocation(
                    roomNumber, ticketRequest.getSeatRow(), ticketRequest.getSeatNumber());
            if (maybeSeat.isEmpty()) {
                throw new InvalidReservationException(
                        "Error. Requested seat is not available."
                );
            }
            Seat seat = maybeSeat.get();
            if (!seatAvailabilityService.
                    seatToAvailabilityByScreeningId(screeningId).get(seat.getId())) {
                throw new InvalidReservationException(
                        "Error. Requested seat is not available."
                );
            }
            result.add(seat);
        }
        return result;
    }

    private void checkIfIsEarlyEnough(ReservationRequest reservationRequest) {
        int requiredScreeningId = reservationRequest.getScreeningId();
        Screening requiredScreening = screeningService.findById(requiredScreeningId);
        LocalDate screeningDate = requiredScreening.getDate();
        LocalTime screeningTime = requiredScreening.getStartTime();
        if (!reservationRequest.isEarlyEnough(screeningDate.atTime(screeningTime))) {
            throw new InvalidReservationException(
                    "Error. The reservation has to be made at least " +
                            "15 minutes before the screening start.");
        }
    }
    private void checkCustomerDataCorrectness(ReservationRequest reservationRequest) {
        if (!reservationRequest.getCustomerData().isValid()) {
            throw new InvalidReservationException(
                    "Error. Invalid customer data for this reservation.");
        }
    }
    private void checkForIsolatedSeat(ReservationRequest reservationRequest) {
        int requiredScreeningId = reservationRequest.getScreeningId();
        Screening requiredScreening = screeningService.findById(requiredScreeningId);
        int roomNumber = requiredScreening.getRoomNumber();

        List<Seat> orderedSeats = seatService.findAllSeatsInRoomSorted(roomNumber);
        RoomLayout roomLayout = new RoomLayout(orderedSeats,
                roomService.findById(roomNumber).getTotalRows());
        Map<Integer, Boolean> seatIdToAvailability =
                seatAvailabilityService.seatToAvailabilityByScreeningId(requiredScreeningId);
        if (reservationRequest.leavesIsolatedSeat(roomLayout, seatIdToAvailability)) {
            throw new InvalidReservationException(
                    "Error. There cannot be one isolated seat left after " +
                            "your reservation is completed.");
        }
    }
}
