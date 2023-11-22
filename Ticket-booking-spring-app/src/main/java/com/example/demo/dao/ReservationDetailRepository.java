package com.example.demo.dao;

import com.example.demo.entity.ReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Integer> {
}
