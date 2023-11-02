package com.example.demo.service;

import com.example.demo.dao.ReservationRepository;
import com.example.demo.dao.TicketPriceRepository;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Screening;
import com.example.demo.entity.SeatAvailability;
import com.example.demo.exception.InvalidReservationException;
import com.example.demo.util.ReservationRequest;
import com.example.demo.util.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class ReservationServiceImpl implements ReservationService{
    private ReservationRepository reservationRepository;
    private ScreeningService screeningService;
    private SeatAvailabilityService seatAvailabilityService;
    private TicketPriceService ticketPriceService;

    @Autowired
    public ReservationServiceImpl(
            ReservationRepository theReservationRepository,
            ScreeningService theScreeningService,
            SeatAvailabilityService theSeatAvailabilityService,
            TicketPriceService theTicketPriceService) {
        reservationRepository = theReservationRepository;
        screeningService = theScreeningService;
        seatAvailabilityService = theSeatAvailabilityService;
        ticketPriceService = theTicketPriceService;
    }

    // check if the seats are chosen according to the cinema policy
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

//        int roomNumber = requiredScreening.getRoomNumber();
//        List<SeatAvailability> seatAvailabilities =
//                seatAvailabilityService.findByScreeningId(requiredScreeningId);


        Map<TicketType, Float> ticketPrices = ticketPriceService.getTicketPrices();
        Reservation newReservation = new Reservation(
            requiredScreeningId,
            reservationRequest.getCustomerData().getName(),
            reservationRequest.getCustomerData().getSurname(),
            reservationRequest.getCost(ticketPrices),
            LocalDateTime.now()
        );
        return reservationRepository.save(newReservation);
    }
}
