package com.example.demo.service.seat;

import com.example.demo.dao.SeatRepository;
import com.example.demo.entity.Seat;
import com.example.demo.service.seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {
    private SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository theSeatRepository) {
        seatRepository = theSeatRepository;
    }

    @Override
    public List<Seat> findByRoomNumber(int roomNumber) {
        return seatRepository.findByRoomNumber(roomNumber);
    }

    @Override
    public Optional<Seat> findByLocation(int roomNumber, char row, int seatNumber) {
        return seatRepository.findByRoomNumberAndRowAndSeatNumber(roomNumber, row, seatNumber);
    }

    @Override
    public Optional<Seat> findById(int seatId) {
        return seatRepository.findById(seatId);
    }

    @Override
    public List<Seat> findAllSeatsInRoomSorted(int roomNumber) {
        return seatRepository.findAllSeatsInRoomSorted(roomNumber);
    }
}
