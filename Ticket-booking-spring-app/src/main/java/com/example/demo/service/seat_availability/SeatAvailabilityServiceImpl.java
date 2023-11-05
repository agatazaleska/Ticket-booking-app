package com.example.demo.service.seat_availability;

import com.example.demo.dao.SeatAvailabilityRepository;
import com.example.demo.entity.Screening;
import com.example.demo.entity.Seat;
import com.example.demo.entity.SeatAvailability;
import com.example.demo.exception.DataBaseException;
import com.example.demo.service.screening.ScreeningService;
import com.example.demo.service.seat.SeatService;
import com.example.demo.service.seat_availability.SeatAvailabilityService;
import com.example.demo.util.SeatAvailabilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SeatAvailabilityServiceImpl implements SeatAvailabilityService {
    private SeatAvailabilityRepository seatAvailabilityRepository;
    private SeatService seatService;
    private ScreeningService screeningService;

    @Autowired
    public SeatAvailabilityServiceImpl(
            SeatAvailabilityRepository theSeatAvailabilityRepository,
            SeatService theSeatService,
            ScreeningService theScreeningService) {
        seatAvailabilityRepository = theSeatAvailabilityRepository;
        seatService = theSeatService;
        screeningService = theScreeningService;
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

    @Override
    public List<SeatAvailabilityResponse> findByScreeningId(int screeningId) {
        Map<Integer, Boolean> availabilities = seatToAvailabilityByScreeningId(screeningId);
        Optional<Screening> maybeScreening = screeningService.findById(screeningId);
        if (maybeScreening.isEmpty()) {
            throw new DataBaseException("Database Error. Room for this screening not found.");
        }
        List<Seat> seats = seatService.findByRoomNumber(maybeScreening.get().getRoomNumber());
        List<SeatAvailabilityResponse> result = new ArrayList<>();
        for (Seat seat : seats) {
            result.add(
                    new SeatAvailabilityResponse(
                           screeningId,
                           seat.getRoomNumber(),
                           seat.getRow(),
                           seat.getRoomNumber(),
                           availabilities.get(seat.getId())
                    )
            );
        }
        return result;
    }
}
