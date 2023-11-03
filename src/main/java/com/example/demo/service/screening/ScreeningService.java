package com.example.demo.service.screening;

import com.example.demo.entity.Screening;
import com.example.demo.util.MovieTimePreferences;

import java.util.List;

public interface ScreeningService {
    List<Screening> findAll();
    List<Screening> findByPreferences(MovieTimePreferences preferences);
    Screening findById(int screeningId);
}