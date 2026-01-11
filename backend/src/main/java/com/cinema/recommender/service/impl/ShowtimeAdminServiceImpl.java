package com.cinema.recommender.service.impl;

import java.util.List;

import com.cinema.recommender.entity.enums.Language;
import com.cinema.recommender.entity.enums.ShowtimeFormat;
import org.springframework.stereotype.Service;

import com.cinema.recommender.dto.CreateShowtimeRequest;
import com.cinema.recommender.entity.Auditorium;
import com.cinema.recommender.entity.Cinema;
import com.cinema.recommender.entity.Movie;
import com.cinema.recommender.entity.Showtime;
import com.cinema.recommender.repository.AuditoriumRepository;
import com.cinema.recommender.repository.MovieRepository;
import com.cinema.recommender.repository.ShowtimeRepository;
import com.cinema.recommender.service.ShowtimeAdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowtimeAdminServiceImpl implements ShowtimeAdminService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final AuditoriumRepository auditoriumRepository;

    @Override
    public Showtime createShowtime(CreateShowtimeRequest request) {

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Auditorium auditorium = auditoriumRepository.findById(request.getAuditoriumId())
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));

        Cinema cinema = auditorium.getCinema();

        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setAuditorium(auditorium);
        showtime.setCinema(cinema);

        showtime.setShowDate(request.getShowDate());
        showtime.setShowTime(request.getShowTime());

        showtime.setFormat(ShowtimeFormat.from(request.getFormat()));
        showtime.setLanguage(Language.from(request.getLanguage()));

        showtime.setBasePrice(request.getBasePrice());
        showtime.setWeekendPrice(request.getWeekendPrice());

        showtime.setTotalSeats(auditorium.getTotalSeats());

        showtime.setAvailableSeats(auditorium.getTotalSeats());

        return showtimeRepository.save(showtime);
    }

    @Override
    public Showtime updateShowtime(Long id, Showtime showtime) {

        Showtime existingShowtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        if (showtime.getMovie() != null && showtime.getMovie().getId() != null) {
            Movie movie = movieRepository.findById(showtime.getMovie().getId())
                    .orElseThrow(() -> new RuntimeException("Movie not found"));
            existingShowtime.setMovie(movie);
        }

        if (showtime.getAuditorium() != null && showtime.getAuditorium().getId() != null) {
            Auditorium auditorium = auditoriumRepository.findById(showtime.getAuditorium().getId())
                    .orElseThrow(() -> new RuntimeException("Auditorium not found"));
            existingShowtime.setAuditorium(auditorium);
        }

        if (showtime.getShowDate() != null) {
            existingShowtime.setShowDate(showtime.getShowDate());
        }

        if (showtime.getShowTime() != null) {
            existingShowtime.setShowTime(showtime.getShowTime());
        }

        if (showtime.getBasePrice() != null) {
            existingShowtime.setBasePrice(showtime.getBasePrice());
        }

        if (showtime.getWeekendPrice() != null) {
            existingShowtime.setWeekendPrice(showtime.getWeekendPrice());
        }

        if (showtime.getAvailableSeats() != null) {
            existingShowtime.setAvailableSeats(showtime.getAvailableSeats());
        }

        if (showtime.getFormat() != null) {
            existingShowtime.setFormat(showtime.getFormat());
        }

        if (showtime.getLanguage() != null) {
            existingShowtime.setLanguage(showtime.getLanguage());
        }

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
