package com.cinema.recommender.repository;

import com.cinema.recommender.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUserId(Long userId);

    boolean existsByUserIdAndMovieId(Long userId, Long movieId);
}
