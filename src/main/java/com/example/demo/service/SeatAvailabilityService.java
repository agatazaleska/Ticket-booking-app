package com.example.demo.service;

import com.example.demo.entity.SeatAvailability;

import java.util.List;

public interface SeatAvailabilityService {
    List<SeatAvailability> findByScreeningId(int screeningId);
}
