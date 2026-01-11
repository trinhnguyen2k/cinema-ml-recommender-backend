package com.cinema.recommender.service;

import com.cinema.recommender.entity.Movie;

import java.util.List;

public interface MovieAdminService {

    Movie createMovie(Movie movie);

    Movie updateMovie(Long id, Movie movie);

    void deleteMovie(Long id);

    List<Movie> getAllMovies();

    Movie getMovieById(Long id);
}
