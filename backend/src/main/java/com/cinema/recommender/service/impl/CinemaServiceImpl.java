package com.cinema.recommender.service.impl;

import com.cinema.recommender.entity.Cinema;
import com.cinema.recommender.repository.CinemaRepository;
import com.cinema.recommender.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;

    @Override
    public Cinema createCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema updateCinema(Long id, Cinema cinema) {
        Cinema existingCinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cinema not found"));

        existingCinema.setName(cinema.getName());
        existingCinema.setAddress(cinema.getAddress());
        existingCinema.setCity(cinema.getCity());

        return cinemaRepository.save(existingCinema);
    }

    @Override
    public void deleteCinema(Long id) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cinema not found"));

        cinemaRepository.delete(cinema);
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema getCinemaById(Long id) {
        return cinemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cinema not found"));
    }
}
