package com.cinema.recommender.service;

import com.cinema.recommender.entity.Showtime;

import java.util.List;

public interface ShowtimeService {

    List<Showtime> getShowtimesByMovie(Long movieId);
}
