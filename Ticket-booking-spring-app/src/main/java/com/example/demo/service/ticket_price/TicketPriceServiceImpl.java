package com.example.demo.service.ticket_price;

import com.example.demo.dao.TicketPriceRepository;
import com.example.demo.entity.TicketPrice;
import com.example.demo.exception.DataBaseException;
import com.example.demo.service.ticket_price.TicketPriceService;
import com.example.demo.util.reservation_request.TicketType;
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
            TicketType enumType = ticketTypeStringToEnum.get(ticketTypeStr);
            if (enumType == null) {
                throw new DataBaseException(
                        "Database Error. Encountered problems while getting " +
                        "ticket prices.");
            }
            result.put(enumType, price);
        }
        return result;
    }
}
