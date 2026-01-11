package com.cinema.recommender.service;

import com.cinema.recommender.entity.Auditorium;

import java.util.List;

public interface AuditoriumAdminService {

    Auditorium createAuditorium(String name, Integer totalSeats, Long cinemaId);

    Auditorium updateAuditorium(Long id, String name, Integer totalSeats);

    void deleteAuditorium(Long id);

    List<Auditorium> getAllAuditoriums();

    Auditorium getAuditoriumById(Long id);
}
