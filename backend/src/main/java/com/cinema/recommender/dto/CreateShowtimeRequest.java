package com.cinema.recommender.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateShowtimeRequest {

    private Long movieId;
    private Long auditoriumId;

    private LocalDate showDate;
    private LocalTime showTime;

    private Integer basePrice;
    private Integer weekendPrice;

    private Integer availableSeats;
}
