package com.example.demo.rest;

import com.example.demo.entity.Reservation;
import com.example.demo.service.ReservationService;
import com.example.demo.util.ReservationRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation_api")
public class ReservationRestController {
    private ReservationService reservationService;
    public ReservationRestController(ReservationService theReservationService) {
        reservationService = theReservationService;
    }

    @PostMapping("/reservations")
    public Reservation RequestReservation(
            @RequestBody ReservationRequest reservationRequest) {
        return reservationService.processReservationRequest(reservationRequest);
    }
}

