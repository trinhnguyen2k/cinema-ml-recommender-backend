package com.cinema.recommender.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "promotions",
        indexes = {
                @Index(name = "idx_code", columnList = "code"),
                @Index(name = "idx_is_active", columnList = "isActive")
        }
)
@Getter
@Setter
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private String description;

    private Integer discountPercent;
    private Integer discountAmount;

    private Integer maxUses;

    @Column(nullable = false)
    private Integer usedCount = 0;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
