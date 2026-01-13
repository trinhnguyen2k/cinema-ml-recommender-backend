package com.cinema.recommender.repository;

import com.cinema.recommender.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long> {

    List<BookingDetail> findByBookingId(Long bookingId);
    boolean existsBySeat_IdAndBooking_Showtime_Id(
            Long seatId,
            Long showtimeId
    );

    @Query("""
    select bd.seat.id
    from BookingDetail bd
    where bd.booking.showtime.id = :showtimeId
""")
    List<Long> findBookedSeatIdsByShowtime(
            @Param("showtimeId") Long showtimeId
    );
}
