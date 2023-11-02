package com.example.demo.util;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

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
