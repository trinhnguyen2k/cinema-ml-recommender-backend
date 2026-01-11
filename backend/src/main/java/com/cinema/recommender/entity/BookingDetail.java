package com.cinema.recommender.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "booking_details",
        uniqueConstraints = @UniqueConstraint(columnNames = {
                "booking_id", "seat_id"
        })
)
@Getter
@Setter
public class BookingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer pricePaid;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    @JsonIgnore
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    @JsonIgnore
    private Seat seat;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
