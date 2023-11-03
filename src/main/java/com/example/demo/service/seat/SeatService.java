package com.example.demo.service.seat;

import com.example.demo.entity.Seat;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface SeatService {
    List<Seat> findByRoomNumber(int roomNumber);
    Optional<Seat> findByLocation(int roomNumber, char row, int seatNumber);
    Optional<Seat> findById(int seatId);
    List<Seat> findAllSeatsInRoomSorted(int roomNumber);
}
