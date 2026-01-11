package com.cinema.recommender.service;

import com.cinema.recommender.dto.CreateShowtimeRequest;
import com.cinema.recommender.entity.Showtime;

import java.util.List;

public interface ShowtimeAdminService {

    Showtime createShowtime(CreateShowtimeRequest request);

    Showtime updateShowtime(Long id, Showtime showtime);

    void deleteShowtime(Long id);

    List<Showtime> getAllShowtimes();

    Showtime getShowtimeById(Long id);
}
