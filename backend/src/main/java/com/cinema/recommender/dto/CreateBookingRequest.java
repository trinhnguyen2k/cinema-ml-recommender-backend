package com.cinema.recommender.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequest {

    private Long userId;
    private Long showtimeId;
    private List<Long> seatIds;
    private String promotionCode;
}
