package com.example.demo.service.seat_availability;

import com.example.demo.dao.SeatAvailabilityRepository;
import com.example.demo.entity.SeatAvailability;
import com.example.demo.service.seat_availability.SeatAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatAvailabilityServiceImpl implements SeatAvailabilityService {
    private SeatAvailabilityRepository seatAvailabilityRepository;

    @Autowired
    public SeatAvailabilityServiceImpl(SeatAvailabilityRepository theSeatAvailabilityRepository) {
        seatAvailabilityRepository = theSeatAvailabilityRepository;
    }

    @Override
    public Map<Integer, Boolean> seatToAvailabilityByScreeningId(int screeningId) {
        List<SeatAvailability> availabilities = seatAvailabilityRepository.
                findByScreeningId(screeningId);
        Map<Integer, Boolean> result = new HashMap<>();
        for (SeatAvailability availability : availabilities) {
            result.put(availability.getSeatId(), availability.getAvailable());
        }
        return result;
    }

    @Override
    public SeatAvailability save(SeatAvailability seatAvailability) {
        return seatAvailabilityRepository.save(seatAvailability);
    }
}
