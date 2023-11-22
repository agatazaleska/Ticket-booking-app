package com.example.demo.service.reservation;

import com.example.demo.dao.ReservationRepository;
import com.example.demo.entity.*;
import com.example.demo.exception.DataBaseException;
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
        Reservation reservation = reservationRepository.
                save(processReservationRequest(reservationRequest));

        for (Seat seat : wantedSeats) {
            ReservationDetail detail = new ReservationDetail(
                    reservation.getId(), seat.getId());
            SeatAvailability availability = new SeatAvailability(
                    reservation.getScreeningId(), seat.getId(), false);

            seatAvailabilityService.save(availability);
            reservationDetailService.save(detail);
        }
        return reservation;
    }

    private Reservation processReservationRequest(ReservationRequest reservationRequest) {
        checkIfAtLeastOneSeat(reservationRequest);
        checkIfIsEarlyEnough(reservationRequest);
        checkCustomerDataCorrectness(reservationRequest);
        checkForIsolatedSeat(reservationRequest);

        int requestedScreeningId = reservationRequest.getScreeningId();
        Screening requestedScreening = getRequestedScreening(reservationRequest);
        Map<TicketType, Float> ticketPrices = ticketPriceService.getTicketPrices();
        return new Reservation(
            requestedScreeningId,
            reservationRequest.getCustomerData().getName(),
            reservationRequest.getCustomerData().getSurname(),
            reservationRequest.getCost(ticketPrices),
                requestedScreening.getDate().atTime(requestedScreening.getStartTime()).
                    minusMinutes(expirationMinutesBeforeScreening)
        );
    }

    private List<Seat> getRequestedSeats(ReservationRequest reservationRequest) {
        List<Seat> result = new ArrayList<>();

        int screeningId = reservationRequest.getScreeningId();
        Screening screening = getRequestedScreening(reservationRequest);
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

    private void checkIfAtLeastOneSeat(ReservationRequest reservationRequest) {
        if (reservationRequest.getRequestedTickets().isEmpty()) {
            throw new InvalidReservationException(
                    "Error. The reservation has to contain at least " +
                    "one seat.");
        }
    }

    private void checkIfIsEarlyEnough(ReservationRequest reservationRequest) {
        Screening requestedScreening = getRequestedScreening(reservationRequest);
        LocalDate screeningDate = requestedScreening.getDate();
        LocalTime screeningTime = requestedScreening.getStartTime();
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
    
    private Screening getRequestedScreening(ReservationRequest reservationRequest) {
        int requestedScreeningId = reservationRequest.getScreeningId();
        Optional<Screening> maybeScreening = screeningService.findById(requestedScreeningId);
        if (maybeScreening.isEmpty()) {
            throw new InvalidReservationException(
                    "Error. requested screening not found.");
        }
        return maybeScreening.get();
    }

    private void checkForIsolatedSeat(ReservationRequest reservationRequest) {
        int requestedScreeningId = reservationRequest.getScreeningId();
        Optional<Screening> maybeScreening = screeningService.findById(requestedScreeningId);
        if (maybeScreening.isEmpty()) {
            throw new InvalidReservationException(
                    "Error. requested screening not found.");
        }
        int roomNumber = maybeScreening.get().getRoomNumber();

        List<Seat> orderedSeats = seatService.findAllSeatsInRoomSorted(roomNumber);
        Optional<Room> maybeRoom = roomService.findByNumber(roomNumber);
        if (maybeRoom.isEmpty()) {
            throw new DataBaseException(
                    "Database Error. Room for this screening not found.");
        }
        RoomLayout roomLayout = new RoomLayout(orderedSeats,
                maybeRoom.get().getTotalRows());
        Map<Integer, Boolean> seatIdToAvailability =
                seatAvailabilityService.seatToAvailabilityByScreeningId(requestedScreeningId);
        if (reservationRequest.leavesIsolatedSeat(roomLayout, seatIdToAvailability)) {
            throw new InvalidReservationException(
                    "Error. There cannot be one isolated seat left after " +
                    "your reservation is completed.");
        }
    }
}
