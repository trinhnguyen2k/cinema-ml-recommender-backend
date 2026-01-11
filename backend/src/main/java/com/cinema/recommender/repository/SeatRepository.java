package com.cinema.recommender.repository;

import com.cinema.recommender.entity.Auditorium;
import com.cinema.recommender.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByAuditoriumId(Long auditoriumId);

    List<Seat> findByAuditorium(Auditorium auditorium);
}
