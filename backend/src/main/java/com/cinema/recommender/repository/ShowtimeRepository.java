package com.cinema.recommender.repository;

import com.cinema.recommender.entity.Movie;
import com.cinema.recommender.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    List<Showtime> findByMovieIdAndShowDate(Long movieId, LocalDate showDate);

    List<Showtime> findByCinemaIdAndShowDate(Long cinemaId, LocalDate showDate);

    List<Showtime> findByMovieOrderByShowDateAscShowTimeAsc(Movie movie);
}
