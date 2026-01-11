package com.cinema.recommender.repository;

import com.cinema.recommender.entity.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {

    List<Auditorium> findByCinemaId(Long cinemaId);
}
