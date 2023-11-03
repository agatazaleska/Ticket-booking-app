package com.example.demo.service;

import com.example.demo.dao.TicketPriceRepository;
import com.example.demo.entity.TicketPrice;
import com.example.demo.util.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TicketPriceServiceImpl implements TicketPriceService {
    private TicketPriceRepository ticketPriceRepository;
    private static final Map<String, TicketType> ticketTypeStringToEnum = new HashMap<>();
    static {
        ticketTypeStringToEnum.put("adult", TicketType.ADULT);
        ticketTypeStringToEnum.put("student", TicketType.STUDENT);
        ticketTypeStringToEnum.put("child", TicketType.CHILD);
    }

    @Autowired
    public TicketPriceServiceImpl(TicketPriceRepository theTicketPriceRepository) {
        ticketPriceRepository = theTicketPriceRepository;
    }

    @Override
    public Map<TicketType, Float> getTicketPrices() {
        Map<TicketType, Float> result = new HashMap<>();
        List<TicketPrice> ticketPrices = ticketPriceRepository.findAll();
        for (TicketPrice ticketPrice : ticketPrices) {
            String ticketTypeStr = ticketPrice.getType();
            Float price = ticketPrice.getPrice();
            // handle incorrect data in database?
            result.put(ticketTypeStringToEnum.get(ticketTypeStr), price);
        }
        return result;
    }
}
