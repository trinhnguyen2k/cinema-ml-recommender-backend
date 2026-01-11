package com.cinema.recommender.repository;

import com.cinema.recommender.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    List<Cinema> findByCityIgnoreCase(String city);
}
