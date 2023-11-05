package com.example.demo.service.ticket_price;

import com.example.demo.util.reservation_request.TicketType;

import java.util.Map;

public interface TicketPriceService {
    Map<TicketType, Float> getTicketPrices();
}
