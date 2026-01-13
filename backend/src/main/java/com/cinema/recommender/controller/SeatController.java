package com.cinema.recommender.controller;

import com.cinema.recommender.dto.SeatAvailabilityResponse;
import com.cinema.recommender.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/showtime/{showtimeId}")
    public List<SeatAvailabilityResponse> getSeatsByShowtime(
            @PathVariable Long showtimeId
    ) {
        return seatService.getSeatsByShowtime(showtimeId);
    }


}
