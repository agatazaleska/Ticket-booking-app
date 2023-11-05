package com.example.demo.util;

public class SeatAvailabilityResponse {
    private int screeningId;
    private int roomNumber;
    private char row;
    private int seatNumber;
    private boolean available;

    public SeatAvailabilityResponse(int screeningId, int roomNumber, char row, int seatNumber, boolean available) {
        this.screeningId = screeningId;
        this.roomNumber = roomNumber;
        this.row = row;
        this.seatNumber = seatNumber;
        this.available = available;
    }
}
