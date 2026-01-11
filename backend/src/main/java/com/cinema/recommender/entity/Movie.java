package com.cinema.recommender.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "movies",
        indexes = {
                @Index(name = "idx_title", columnList = "title"),
                @Index(name = "idx_release_date", columnList = "releaseDate")
        }
)
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String posterUrl;
    private String backgroundUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal rating = BigDecimal.ZERO;

    private LocalDate releaseDate;
    private Integer duration;
    private String ageRating;
    private String trailerUrl;

    private String genres;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
