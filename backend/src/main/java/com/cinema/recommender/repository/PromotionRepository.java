package com.cinema.recommender.repository;

import com.cinema.recommender.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Optional<Promotion> findByCodeAndIsActiveTrue(String code);

    boolean existsByCode(String code);

    List<Promotion> findByIsActiveTrueAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate today1,
            LocalDate today2
    );
}
