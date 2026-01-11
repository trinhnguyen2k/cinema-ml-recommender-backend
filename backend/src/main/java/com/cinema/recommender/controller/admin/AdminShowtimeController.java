package com.cinema.recommender.controller.admin;

import com.cinema.recommender.entity.Showtime;
import com.cinema.recommender.service.ShowtimeAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/showtimes")
@RequiredArgsConstructor
public class AdminShowtimeController {

    private final ShowtimeAdminService showtimeAdminService;

    @PostMapping
    public Showtime createShowtime(@RequestBody Showtime showtime) {
        return showtimeAdminService.createShowtime(showtime);
    }

    @PutMapping("/{id}")
    public Showtime updateShowtime(
            @PathVariable Long id,
            @RequestBody Showtime showtime
    ) {
        return showtimeAdminService.updateShowtime(id, showtime);
    }

    @DeleteMapping("/{id}")
    public void deleteShowtime(@PathVariable Long id) {
        showtimeAdminService.deleteShowtime(id);
    }

    @GetMapping
    public List<Showtime> getAllShowtimes() {
        return showtimeAdminService.getAllShowtimes();
    }

    @GetMapping("/{id}")
    public Showtime getShowtimeById(@PathVariable Long id) {
        return showtimeAdminService.getShowtimeById(id);
    }
}
