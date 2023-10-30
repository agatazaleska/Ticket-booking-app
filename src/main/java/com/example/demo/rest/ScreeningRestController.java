package com.example.demo.rest;

import com.example.demo.entity.Screening;
import com.example.demo.service.ScreeningService;
import com.example.demo.util.MovieTimePreferences;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScreeningRestController {
    private ScreeningService screeningService;
    // quick and dirty: inject employee dao
    public ScreeningRestController(ScreeningService theScreeningService) {
        screeningService = theScreeningService;
    }

    @GetMapping("/screenings")
    public List<Screening> getScreenings(
            @RequestParam LocalDate date,
            @RequestParam LocalTime earliestTime,
            @RequestParam LocalTime latestTime) {
        MovieTimePreferences preferences = new MovieTimePreferences(
                date, earliestTime, latestTime);
        return screeningService.findByPreferences(preferences);
    }
}
