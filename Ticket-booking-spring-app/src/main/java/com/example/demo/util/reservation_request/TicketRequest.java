package com.example.demo.util.reservation_request;

public class TicketRequest {
    private char seatRow;
    private int seatNumber;
    private TicketType ticketType;

    public TicketRequest(char seatRow, int seatNumber, TicketType ticketType) {
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.ticketType = ticketType;
    }

    public char getSeatRow() {
        return seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}
