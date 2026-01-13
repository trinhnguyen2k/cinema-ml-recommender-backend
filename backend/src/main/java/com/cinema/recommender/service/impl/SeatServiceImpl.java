package com.cinema.recommender.service.impl;

import com.cinema.recommender.dto.SeatAvailabilityResponse;
import com.cinema.recommender.entity.Seat;
import com.cinema.recommender.entity.Showtime;
import com.cinema.recommender.exception.NotFoundException;
import com.cinema.recommender.repository.BookingDetailRepository;
import com.cinema.recommender.repository.SeatRepository;
import com.cinema.recommender.repository.ShowtimeRepository;
import com.cinema.recommender.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final ShowtimeRepository showtimeRepository;
    private final SeatRepository seatRepository;
    private final BookingDetailRepository bookingDetailRepository;

    @Override
    public List<SeatAvailabilityResponse> getSeatsByShowtime(Long showtimeId) {

        // Lấy showtime
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new NotFoundException("Showtime not found"));

        //  Lấy tất cả seat của auditorium
        List<Seat> allSeats =
                seatRepository.findByAuditorium(showtime.getAuditorium());

        // Lấy seatId đã book
        List<Long> bookedSeatIds =
                bookingDetailRepository.findBookedSeatIdsByShowtime(showtimeId);

        // Map sang response
        return allSeats.stream()
                .map(seat -> {
                    boolean available = !bookedSeatIds.contains(seat.getId());

                    int price = showtime.getBasePrice() + seat.getExtraPrice();

                    System.out.println(bookedSeatIds);
                    System.out.println(allSeats.size());
                    return new SeatAvailabilityResponse(
                            seat.getId(),
                            seat.getRowLetter(),
                            seat.getNumber(),
                            seat.getSeatType().name(),
                            price,
                            available
                    );
                })
                .toList();

    }
}

