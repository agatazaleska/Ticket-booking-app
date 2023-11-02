package com.example.demo.service;

import com.example.demo.dao.ReservationRepository;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Screening;
import com.example.demo.exception.InvalidReservationException;
import com.example.demo.util.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ReservationServiceImpl implements ReservationService{
    private ReservationRepository reservationRepository;
    private ScreeningService screeningService;

    @Autowired
    public ReservationServiceImpl(
            ReservationRepository theReservationRepository,
            ScreeningService theScreeningService) {
        reservationRepository = theReservationRepository;
        screeningService = theScreeningService;
    }

    // check if the seats are chosen according to the cinema policy
    // reservation is valid - calculate total price
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

        Reservation newReservation = new Reservation(
            requiredScreeningId,
            reservationRequest.getCustomerData().getName(),
            reservationRequest.getCustomerData().getSurname(),
            10,
            LocalDateTime.now()
        );
        return reservationRepository.save(newReservation);
    }
}
