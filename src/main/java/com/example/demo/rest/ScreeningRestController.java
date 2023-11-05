package com.example.demo.rest;

import com.example.demo.entity.Screening;
import com.example.demo.exception.ScreeningException;
import com.example.demo.service.screening.ScreeningService;
import com.example.demo.util.MovieTimePreferences;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/screening_api")
public class ScreeningRestController {
    private ScreeningService screeningService;
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

    @GetMapping("/screenings/{screeningId}")
    public Screening findById(@PathVariable int screeningId) {
        Optional<Screening> maybeScreening = screeningService.findById(screeningId);
        if (maybeScreening.isEmpty()) {
            throw new ScreeningException(
                    "Error. Screening not found."
            );
        }
        return maybeScreening.get();
    }
}
