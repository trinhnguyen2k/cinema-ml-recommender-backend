package com.cinema.recommender.service;

import com.cinema.recommender.entity.Cinema;

import java.util.List;

public interface CinemaService {

    Cinema createCinema(Cinema cinema);

    Cinema updateCinema(Long id, Cinema cinema);

    void deleteCinema(Long id);

    List<Cinema> getAllCinemas();

    Cinema getCinemaById(Long id);
}
