package com.cinema.recommender.controller;

import com.cinema.recommender.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.recommender.dto.CreateBookingRequest;
import com.cinema.recommender.entity.Booking;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody CreateBookingRequest request) {

        return bookingService.createBooking(
                request.getUserId(),
                request.getShowtimeId(),
                request.getSeatIds(),
                request.getPromotionCode()
        );
    }
}
