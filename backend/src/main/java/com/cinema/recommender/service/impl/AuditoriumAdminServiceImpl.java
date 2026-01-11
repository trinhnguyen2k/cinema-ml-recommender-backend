package com.cinema.recommender.service.impl;

import com.cinema.recommender.entity.Auditorium;
import com.cinema.recommender.entity.Cinema;
import com.cinema.recommender.repository.AuditoriumRepository;
import com.cinema.recommender.repository.CinemaRepository;
import com.cinema.recommender.service.AuditoriumAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriumAdminServiceImpl implements AuditoriumAdminService {

    private final AuditoriumRepository auditoriumRepository;
    private final CinemaRepository cinemaRepository;

    @Override
    public Auditorium createAuditorium(String name, Integer totalSeats, Long cinemaId) {

        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new RuntimeException("Cinema not found"));

        Auditorium auditorium = new Auditorium();
        auditorium.setName(name);
        auditorium.setTotalSeats(totalSeats);
        auditorium.setCinema(cinema);

        return auditoriumRepository.save(auditorium);
    }

    @Override
    public Auditorium updateAuditorium(Long id, String name, Integer totalSeats) {

        Auditorium auditorium = auditoriumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));

        auditorium.setName(name);
        auditorium.setTotalSeats(totalSeats);

        return auditoriumRepository.save(auditorium);
    }

    @Override
    public void deleteAuditorium(Long id) {
        Auditorium auditorium = auditoriumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));

        auditoriumRepository.delete(auditorium);
    }

    @Override
    public List<Auditorium> getAllAuditoriums() {
        return auditoriumRepository.findAll();
    }

    @Override
    public Auditorium getAuditoriumById(Long id) {
        return auditoriumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));
    }
}
