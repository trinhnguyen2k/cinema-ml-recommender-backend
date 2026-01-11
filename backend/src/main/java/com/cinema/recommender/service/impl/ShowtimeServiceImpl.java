package com.cinema.recommender.service.impl;

import com.cinema.recommender.entity.Movie;
import com.cinema.recommender.entity.Showtime;
import com.cinema.recommender.repository.MovieRepository;
import com.cinema.recommender.repository.ShowtimeRepository;
import com.cinema.recommender.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;

    @Override
    public List<Showtime> getShowtimesByMovie(Long movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        return showtimeRepository.findByMovieOrderByShowDateAscShowTimeAsc(movie);
    }
}
