package com.cinema.recommender.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SeatAvailabilityResponse {

    private Long seatId;
    private String rowLetter;
    private Integer number;
    private String seatType;
    private Integer price;
    private boolean available;
}
