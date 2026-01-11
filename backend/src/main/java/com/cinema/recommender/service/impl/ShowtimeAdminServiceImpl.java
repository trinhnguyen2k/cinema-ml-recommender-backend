package com.cinema.recommender.service.impl;

import com.cinema.recommender.entity.Auditorium;
import com.cinema.recommender.entity.Movie;
import com.cinema.recommender.entity.Showtime;
import com.cinema.recommender.repository.AuditoriumRepository;
import com.cinema.recommender.repository.MovieRepository;
import com.cinema.recommender.repository.ShowtimeRepository;
import com.cinema.recommender.service.ShowtimeAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowtimeAdminServiceImpl implements ShowtimeAdminService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final AuditoriumRepository auditoriumRepository;

    @Override
    public Showtime createShowtime(Showtime showtime) {

        Movie movie = movieRepository.findById(showtime.getMovie().getId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Auditorium auditorium = auditoriumRepository.findById(showtime.getAuditorium().getId())
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));

        showtime.setMovie(movie);
        showtime.setAuditorium(auditorium);

        return showtimeRepository.save(showtime);
    }

    @Override
    public Showtime updateShowtime(Long id, Showtime showtime) {

        Showtime existingShowtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        Movie movie = movieRepository.findById(showtime.getMovie().getId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Auditorium auditorium = auditoriumRepository.findById(showtime.getAuditorium().getId())
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));

        existingShowtime.setMovie(movie);
        existingShowtime.setAuditorium(auditorium);
        existingShowtime.setShowDate(showtime.getShowDate());
        existingShowtime.setShowTime(showtime.getShowTime());
        existingShowtime.setBasePrice(showtime.getBasePrice());
        existingShowtime.setWeekendPrice(showtime.getWeekendPrice());
        existingShowtime.setAvailableSeats(showtime.getAvailableSeats());

        return showtimeRepository.save(existingShowtime);
    }

    @Override
    public void deleteShowtime(Long id) {
        Showtime showtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        showtimeRepository.delete(showtime);
    }

    @Override
    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    @Override
    public Showtime getShowtimeById(Long id) {
        return showtimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));
    }
}
