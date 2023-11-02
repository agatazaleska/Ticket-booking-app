package com.example.demo.service;

import com.example.demo.entity.Reservation;
import com.example.demo.util.ReservationRequest;

import java.time.LocalDateTime;

public class ReservationServiceImpl implements ReservationService{
    // check if it is not too late to make the reservation
    // check if customer data is correct
    // check if the seats are chosen according to the cinema policy
    // reservation is valid - calculate total price
    @Override
    public Reservation processReservationRequest(ReservationRequest reservationRequest) {
        Reservation newReservation = new Reservation(
            reservationRequest.getCustomerData().getName(),
            reservationRequest.getCustomerData().getSurname(),
            10,
            LocalDateTime.now()
        );
        return null;
    }
}
