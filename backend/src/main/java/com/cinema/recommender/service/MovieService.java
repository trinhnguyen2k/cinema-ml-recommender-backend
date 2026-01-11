package com.cinema.recommender.service;

import com.cinema.recommender.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();

    Movie getMovieById(Long id);
}
