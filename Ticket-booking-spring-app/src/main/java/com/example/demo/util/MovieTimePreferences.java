package com.example.demo.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class MovieTimePreferences {
    private final LocalDate date;
    private final LocalTime earliestStart;
    private final LocalTime latestStart;

    public MovieTimePreferences(LocalDate date, LocalTime earliest_start, LocalTime latest_start) {
        this.date = date;
        this.earliestStart = earliest_start;
        this.latestStart = latest_start;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getEarliestStart() {
        return earliestStart;
    }

    public LocalTime getLatestStart() {
        return latestStart;
    }
}
