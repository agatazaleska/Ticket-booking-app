package com.example.demo.service;

import com.example.demo.dao.SeatAvailabilityRepository;
import com.example.demo.entity.SeatAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatAvailabilityServiceImpl implements SeatAvailabilityService {
    private SeatAvailabilityRepository seatAvailabilityRepository;

    @Autowired
    public SeatAvailabilityServiceImpl(SeatAvailabilityRepository theSeatAvailabilityRepository) {
        seatAvailabilityRepository = theSeatAvailabilityRepository;
    }

    @Override
    public List<SeatAvailability> findByScreeningId(int screeningId) {
        return seatAvailabilityRepository.findByScreeningId(screeningId);
    }
}
