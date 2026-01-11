package com.cinema.recommender.dto;

import com.cinema.recommender.entity.enums.Language;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateShowtimeRequest {
    private String format;

    private Long movieId;
    private Long auditoriumId;
    private String language;
    private LocalDate showDate;
    private LocalTime showTime;

    private Integer basePrice;
    private Integer weekendPrice;

    private Integer availableSeats;
}
