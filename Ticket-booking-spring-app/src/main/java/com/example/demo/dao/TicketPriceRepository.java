package com.example.demo.dao;

import com.example.demo.entity.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPriceRepository extends JpaRepository<TicketPrice, String> {
}
