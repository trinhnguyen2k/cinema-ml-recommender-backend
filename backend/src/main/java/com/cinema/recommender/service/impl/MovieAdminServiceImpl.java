package com.cinema.recommender.service.impl;

import com.cinema.recommender.entity.Movie;
import com.cinema.recommender.repository.MovieRepository;
import com.cinema.recommender.service.MovieAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieAdminServiceImpl implements MovieAdminService {

    private final MovieRepository movieRepository;

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setDuration(movie.getDuration());
        existingMovie.setAgeRating(movie.getAgeRating());
        existingMovie.setTrailerUrl(movie.getTrailerUrl());
        existingMovie.setPosterUrl(movie.getPosterUrl());
        existingMovie.setGenres(movie.getGenres());

        return movieRepository.save(existingMovie);
    }

    @Override
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movieRepository.delete(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }
}
