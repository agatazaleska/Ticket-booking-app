package com.example.demo.dao;

import com.example.demo.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
    // that's it ... no need to write any code LOL!
}
