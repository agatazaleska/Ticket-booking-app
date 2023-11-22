package com.example.demo.rest;

import com.example.demo.entity.SeatAvailability;
import com.example.demo.service.seat_availability.SeatAvailabilityService;
import com.example.demo.util.SeatAvailabilityResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seat_availability_api")
public class SeatAvailabilityRestController {
    private SeatAvailabilityService seatAvailabilityService;

    public SeatAvailabilityRestController(SeatAvailabilityService theSeatAvailabilityService) {
        seatAvailabilityService = theSeatAvailabilityService;
    }

    @GetMapping("/seat_availabilities/{screeningId}")
    List<SeatAvailabilityResponse> findByScreeningId(@PathVariable int screeningId) {
        return seatAvailabilityService.findByScreeningId(screeningId);
    }
}
