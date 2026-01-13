package com.cinema.recommender.repository;

import com.cinema.recommender.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long> {

    List<BookingDetail> findByBookingId(Long bookingId);
    boolean existsBySeat_IdAndBooking_Showtime_Id(
            Long seatId,
            Long showtimeId
    );
}
