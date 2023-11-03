package com.example.demo.service.seat_availability;

import com.example.demo.entity.SeatAvailability;

import java.util.List;
import java.util.Map;

public interface SeatAvailabilityService {
    Map<Integer, Boolean> seatToAvailabilityByScreeningId(int screeningId);
}
