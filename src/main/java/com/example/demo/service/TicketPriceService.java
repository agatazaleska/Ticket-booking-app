package com.example.demo.service;

import com.example.demo.util.TicketType;

import java.util.Map;

public interface TicketPriceService {
    Map<TicketType, Float> getTicketPrices();
}
