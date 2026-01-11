package com.cinema.recommender.controller.admin;

import com.cinema.recommender.entity.Movie;
import com.cinema.recommender.service.MovieAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class AdminMovieController {

    private final MovieAdminService movieAdminService;

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieAdminService.createMovie(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(
            @PathVariable Long id,
            @RequestBody Movie movie
    ) {
        return movieAdminService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieAdminService.deleteMovie(id);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieAdminService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieAdminService.getMovieById(id);
    }
}
