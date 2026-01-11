package com.cinema.recommender.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAuditoriumRequest {

    private String name;
    private Integer totalSeats;
    private Long cinemaId;
}
