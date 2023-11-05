package com.example.demo.util;

public class SeatAvailabilityResponse {
    private int screeningId;
    private int roomNumber;
    private char row;
    private int seatNumber;
    private boolean available;

    public SeatAvailabilityResponse() {}

    public SeatAvailabilityResponse(int screeningId, int roomNumber, char row, int seatNumber, boolean available) {
        this.screeningId = screeningId;
        this.roomNumber = roomNumber;
        this.row = row;
        this.seatNumber = seatNumber;
        this.available = available;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
