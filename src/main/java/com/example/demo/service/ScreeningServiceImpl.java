package com.example.demo.service;

import com.example.demo.dao.ScreeningRepository;
import com.example.demo.entity.Screening;
import com.example.demo.util.MovieTimePreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {
    private ScreeningRepository screeningRepository;

    @Autowired
    ScreeningServiceImpl(ScreeningRepository theScreeningRepository) {
        screeningRepository = theScreeningRepository;
    }

    @Override
    public List<Screening> findAll() {
        return screeningRepository.findAll();
    }

    @Override
    public List<Screening> findByPreferences(MovieTimePreferences preferences) {
        return screeningRepository.findByDateAndStartTimeBetween(
                preferences.getDate(),
                preferences.getEarliestStart(),
                preferences.getLatestStart());
    }
}
