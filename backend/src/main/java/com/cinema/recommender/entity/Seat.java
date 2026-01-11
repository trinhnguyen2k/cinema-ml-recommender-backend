package com.cinema.recommender.entity;

import com.cinema.recommender.entity.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "seats",
        uniqueConstraints = @UniqueConstraint(columnNames = {
                "auditorium_id", "row_letter", "number"
        })
)
@Getter
@Setter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row_letter", length = 1, nullable = false)
    private String rowLetter;

    @Column(nullable = false)
    private Integer number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType seatType;

    @Column(nullable = false)
    private Integer basePrice = 90000;

    @Column(nullable = false)
    private Integer extraPrice = 0;

    @ManyToOne
    @JoinColumn(name = "auditorium_id", nullable = false)
    @JsonIgnore
    private Auditorium auditorium;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
