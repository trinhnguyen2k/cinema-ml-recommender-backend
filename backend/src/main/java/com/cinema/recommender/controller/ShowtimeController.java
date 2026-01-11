package com.cinema.recommender.controller;

import com.cinema.recommender.entity.Showtime;
import com.cinema.recommender.service.ShowtimeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping("/movie/{movieId}")
    public List<Showtime> getShowtimesByMovie(@PathVariable Long movieId) {
        return showtimeService.getShowtimesByMovie(movieId);
    }
}
