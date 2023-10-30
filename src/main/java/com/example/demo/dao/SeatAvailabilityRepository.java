package com.example.demo.dao;

import com.example.demo.entity.SeatAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatAvailabilityRepository extends JpaRepository<SeatAvailability, Integer> {
}
