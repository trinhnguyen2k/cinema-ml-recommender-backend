package com.cinema.recommender.service;

import com.cinema.recommender.dto.CreateBookingRequest;
import com.cinema.recommender.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(CreateBookingRequest request);

    List<Booking> getUserBookings(Long userId);
}
