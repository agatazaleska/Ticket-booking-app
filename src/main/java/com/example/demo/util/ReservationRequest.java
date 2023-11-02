package com.example.demo.util;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ReservationRequest {
    private static final int acceptableMinutesBeforeScreening = 15;
    private int screeningId;
    private List<TicketRequest> requestedTickets;
    private CustomerData customerData;

    public ReservationRequest(int screeningId, List<TicketRequest> requestedTickets, CustomerData customerData) {
        this.screeningId = screeningId;
        this.requestedTickets = requestedTickets;
        this.customerData = customerData;
    }

    public boolean isEarlyEnough(LocalDateTime screeningTime) {
        return LocalDateTime.now()
                            .plusMinutes(acceptableMinutesBeforeScreening)
                            .isBefore(screeningTime);
    }

    public float getCost(Map<TicketType, Float> prices) {
        float totalCost = 0;
        for (TicketRequest ticketRequest : requestedTickets) {
            totalCost += prices.get(ticketRequest.getTicketType());
        }
        return totalCost;
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
