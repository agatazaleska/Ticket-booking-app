package com.example.demo.dao;

import com.example.demo.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
    List<Screening> findByDateAndStartTimeBetween(LocalDate date, LocalTime startTime, LocalTime endTime);
}
