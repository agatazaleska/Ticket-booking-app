package com.example.demo.util;

import com.example.demo.entity.Seat;

import java.util.List;
import java.util.Optional;

public class RoomLayout {
    List<Seat> orderedSeats;
    int rows;

    public RoomLayout(List<Seat> theOrderedSeats, int theRows) {
         orderedSeats = theOrderedSeats;
         rows = theRows;
    }

    public Optional<Seat> SeatToTheRight(char row, int seatNumber) {
        int seatIndex = (row - 'A') * rows + (seatNumber);
        return GetSeatIfInTheSameRow(row, seatIndex);
    }

    public Optional<Seat> SeatToTheLeft(char row, int seatNumber) {
        int seatIndex = (row - 'A') * rows + (seatNumber - 2);
        return GetSeatIfInTheSameRow(row, seatIndex);
    }

    public Optional<Seat> GetSeatIfInTheSameRow(char row, int seatIndex) {
        if (seatIndex < 0 || seatIndex >= orderedSeats.size()) {
            return Optional.empty();
        }
        Seat maybeSeat = orderedSeats.get(seatIndex);
        if (maybeSeat.getRow() != row) {
            return Optional.empty();
        }
        return Optional.of(maybeSeat);
    }
}
