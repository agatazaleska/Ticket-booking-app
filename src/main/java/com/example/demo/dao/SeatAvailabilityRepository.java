package com.example.demo.dao;

import com.example.demo.entity.SeatAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatAvailabilityRepository extends JpaRepository<SeatAvailability, Integer> {
    List<SeatAvailability> findByScreeningId(int screeningId);
}
