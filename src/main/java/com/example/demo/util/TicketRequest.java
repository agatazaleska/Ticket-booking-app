package com.example.demo.util;

public class TicketRequest {
    private int seatId;
    private String ticketType; // enum?

    public TicketRequest(int seatId, String ticketType) {
        this.seatId = seatId;
        this.ticketType = ticketType;
    }
}
