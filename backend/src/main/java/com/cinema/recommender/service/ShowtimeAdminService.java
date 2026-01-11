package com.cinema.recommender.service;

import com.cinema.recommender.entity.Showtime;

import java.util.List;

public interface ShowtimeAdminService {

    Showtime createShowtime(Showtime showtime);

    Showtime updateShowtime(Long id, Showtime showtime);

    void deleteShowtime(Long id);

    List<Showtime> getAllShowtimes();

    Showtime getShowtimeById(Long id);
}
