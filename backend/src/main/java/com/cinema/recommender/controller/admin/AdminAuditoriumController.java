package com.cinema.recommender.controller.admin;

import com.cinema.recommender.dto.CreateAuditoriumRequest;
import com.cinema.recommender.entity.Auditorium;
import com.cinema.recommender.service.AuditoriumAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/auditoriums")
@RequiredArgsConstructor
public class AdminAuditoriumController {

    private final AuditoriumAdminService auditoriumAdminService;

    @PostMapping
    public Auditorium createAuditorium(@RequestBody CreateAuditoriumRequest request) {
        return auditoriumAdminService.createAuditorium(
                request.getName(),
                request.getTotalSeats(),
                request.getCinemaId()
        );
    }

    @GetMapping
    public List<Auditorium> getAllAuditoriums() {
        return auditoriumAdminService.getAllAuditoriums();
    }

    @GetMapping("/{id}")
    public Auditorium getAuditorium(@PathVariable Long id) {
        return auditoriumAdminService.getAuditoriumById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuditorium(@PathVariable Long id) {
        auditoriumAdminService.deleteAuditorium(id);
    }
}
