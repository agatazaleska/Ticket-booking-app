package com.example.demo.service;

import com.example.demo.entity.Reservation;
import com.example.demo.util.ReservationRequest;

public interface ReservationService {
    Reservation processReservationRequest(ReservationRequest reservationRequest);
}
