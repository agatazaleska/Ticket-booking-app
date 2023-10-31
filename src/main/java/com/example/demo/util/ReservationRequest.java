package com.example.demo.util;

import java.util.List;

public class ReservationRequest {
    private int screeningId;
    private List<TicketRequest> requestedTickets;
    private CustomerData customerData;

    public ReservationRequest(int screeningId, List<TicketRequest> requestedTickets, CustomerData customerData) {
        this.screeningId = screeningId;
        this.requestedTickets = requestedTickets;
        this.customerData = customerData;
    }

    public int getScreeningId() {
        return screeningId;
    }

    public List<TicketRequest> getRequestedTickets() {
        return requestedTickets;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }
}
