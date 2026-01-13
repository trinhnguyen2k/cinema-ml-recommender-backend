package com.cinema.recommender.service.impl;

import com.cinema.recommender.entity.Seat;
import com.cinema.recommender.entity.Showtime;
import com.cinema.recommender.exception.NotFoundException;
import com.cinema.recommender.repository.SeatRepository;
import com.cinema.recommender.repository.ShowtimeRepository;
import com.cinema.recommender.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final ShowtimeRepository showtimeRepository;

    @Override
    public List<Seat> getSeatsByShowtime(Long showtimeId) {

        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new NotFoundException("Showtime not found"));

        return seatRepository.findByAuditorium(showtime.getAuditorium());
    }
}
