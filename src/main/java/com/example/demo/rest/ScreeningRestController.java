package com.example.demo.rest;

import com.example.demo.entity.Screening;
import com.example.demo.service.ScreeningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Screening> getAll() {
        return screeningService.findAll();
    }
}
