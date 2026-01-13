package com.cinema.recommender.service;

import com.cinema.recommender.dto.SeatAvailabilityResponse;
import java.util.List;

public interface SeatService {

    List<SeatAvailabilityResponse> getSeatsByShowtime(Long showtimeId);

}
