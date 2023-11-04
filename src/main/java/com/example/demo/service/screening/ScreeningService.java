package com.example.demo.service.screening;

import com.example.demo.entity.Screening;
import com.example.demo.util.MovieTimePreferences;

import java.util.List;
import java.util.Optional;

public interface ScreeningService {
    List<Screening> findAll();
    List<Screening> findByPreferences(MovieTimePreferences preferences);
    Optional<Screening> findById(int screeningId);
}
