package com.example.demo.service.reservation;

import com.example.demo.dao.ReservationRepository;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Screening;
import com.example.demo.entity.Seat;
import com.example.demo.exception.InvalidReservationException;
import com.example.demo.service.room.RoomService;
import com.example.demo.service.screening.ScreeningService;
import com.example.demo.service.seat.SeatService;
import com.example.demo.service.seat_availability.SeatAvailabilityService;
import com.example.demo.service.ticket_price.TicketPriceService;
import com.example.demo.util.reservation_request.ReservationRequest;
import com.example.demo.util.RoomLayout;
import com.example.demo.util.reservation_request.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static int expirationMinutesBeforeScreening = 10;
    private ReservationRepository reservationRepository;
    private ScreeningService screeningService;
    private SeatAvailabilityService seatAvailabilityService;
    private SeatService seatService;
    private TicketPriceService ticketPriceService;
    private RoomService roomService;

    @Autowired
    public ReservationServiceImpl(
            ReservationRepository theReservationRepository,
            ScreeningService theScreeningService,
            SeatAvailabilityService theSeatAvailabilityService,
            TicketPriceService theTicketPriceService,
            SeatService theSeatService,
            RoomService theRoomService) {
        reservationRepository = theReservationRepository;
        screeningService = theScreeningService;
        seatAvailabilityService = theSeatAvailabilityService;
        ticketPriceService = theTicketPriceService;
        seatService = theSeatService;
        roomService = theRoomService;
    }

    @Override
    public Reservation processReservationRequest(ReservationRequest reservationRequest) {
        int requiredScreeningId = reservationRequest.getScreeningId();
        Screening requiredScreening = screeningService.findById(requiredScreeningId);
        LocalDate screeningDate = requiredScreening.getDate();
        LocalTime screeningTime = requiredScreening.getStartTime();
        if (!reservationRequest.isEarlyEnough(screeningDate.atTime(screeningTime))) {
            throw new InvalidReservationException(
                    "Error. The reservation has to be made at least " +
                    "15 minutes before the screening start.");
        }
        if (!reservationRequest.getCustomerData().isValid()) {
            throw new InvalidReservationException(
                    "Error. Invalid customer data for this reservation.");
        }
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

        Map<TicketType, Float> ticketPrices = ticketPriceService.getTicketPrices();
        Reservation newReservation = new Reservation(
            requiredScreeningId,
            reservationRequest.getCustomerData().getName(),
            reservationRequest.getCustomerData().getSurname(),
            reservationRequest.getCost(ticketPrices),
            screeningDate.atTime(screeningTime).
                    minusMinutes(expirationMinutesBeforeScreening)
        );
        return reservationRepository.save(newReservation);
    }
}
