package com.cinema.recommender.service;

import com.cinema.recommender.entity.Seat;

import java.util.List;

public interface SeatService {

    List<Seat> getSeatsByShowtime(Long showtimeId);
}
