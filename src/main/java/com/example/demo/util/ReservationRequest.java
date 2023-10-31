package com.example.demo.util;

import java.util.List;

public class ReservationRequest {
    private int screeningId;
    private List<TicketRequest> requestedTickets;
    private String customerName;
    private String customerSurname;

    public ReservationRequest(int screeningId, List<TicketRequest> requestedTickets, String customerName, String customerSurname) {
        this.screeningId = screeningId;
        this.requestedTickets = requestedTickets;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
    }
}
