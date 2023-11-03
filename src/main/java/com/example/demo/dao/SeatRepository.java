package com.example.demo.dao;

import com.example.demo.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByRoomNumber(int roomNumber);
    Optional<Seat> findByRoomNumberAndRowAndSeatNumber(int roomNumber, char row, int seatNumber);
    @Query("SELECT s FROM Seat s WHERE s.roomNumber = :roomNumber ORDER BY s.row, s.seatNumber")
    List<Seat> findAllSeatsInRoomSorted(int roomNumber);
}
