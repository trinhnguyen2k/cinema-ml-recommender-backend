package com.cinema.recommender.service;

import com.cinema.recommender.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(
            Long userId,
            Long showtimeId,
            List<Long> seatIds,
            String promotionCode
    );

    List<Booking> getUserBookings(Long userId);
}
