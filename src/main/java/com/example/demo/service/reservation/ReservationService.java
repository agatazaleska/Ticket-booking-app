package com.example.demo.service.reservation;

import com.example.demo.entity.Reservation;
import com.example.demo.util.reservation_request.ReservationRequest;

public interface ReservationService {
    Reservation postReservation(ReservationRequest reservationRequest);
}
