package com.example.demo.util;

public class TicketRequest {
    private int seatId;
    private TicketType ticketType; // enum?

    public TicketRequest(int seatId, TicketType ticketType) {
        this.seatId = seatId;
        this.ticketType = ticketType;
    }

    public int getSeatId() {
        return seatId;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}
