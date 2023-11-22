package com.example.demo.service.seat_availability;

import com.example.demo.entity.SeatAvailability;
import com.example.demo.util.SeatAvailabilityResponse;

import java.util.List;
import java.util.Map;

public interface SeatAvailabilityService {
    Map<Integer, Boolean> seatToAvailabilityByScreeningId(int screeningId);
    SeatAvailability save(SeatAvailability seatAvailability);
    List<SeatAvailabilityResponse> findByScreeningId(int screeningId);
}
