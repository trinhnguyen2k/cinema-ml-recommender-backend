package com.cinema.recommender.service.impl;

import com.cinema.recommender.entity.*;
import com.cinema.recommender.entity.enums.BookingStatus;
import com.cinema.recommender.entity.enums.PaymentStatus;
import com.cinema.recommender.repository.*;
import com.cinema.recommender.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingDetailRepository bookingDetailRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;

    @Override
    @Transactional
    public Booking createBooking(
            Long userId,
            Long showtimeId,
            List<Long> seatIds,
            String promotionCode
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        // Validate ghe va ghe trong
        List<Seat> seats = seatRepository.findAllById(seatIds);
        if (seats.size() != seatIds.size()) {
            throw new RuntimeException("Some seats not found");
        }

        if (seats.size() > showtime.getAvailableSeats()) {
            throw new RuntimeException("Not enough available seats");
        }

        // Xac dinh gia ve theo ngay
        int baseTicketPrice;
        switch (showtime.getShowDate().getDayOfWeek()) {
            case SATURDAY:
            case SUNDAY:
                baseTicketPrice = showtime.getWeekendPrice();
                break;
            default:
                baseTicketPrice = showtime.getBasePrice();
        }

        int totalPrice = seats.stream()
                .mapToInt(seat -> baseTicketPrice + seat.getExtraPrice())
                .sum();

        // Kiem tra promotion code
        if (promotionCode != null && !promotionCode.isBlank()) {
            // Check promotion code valid
            Promotion promotion = promotionRepository
                    .findByCodeAndIsActiveTrue(promotionCode)
                    .orElseThrow(() -> new RuntimeException("Invalid promotion code"));
            //Ap dung giam gia
            if (promotion.getDiscountPercent() != null) {
                totalPrice -= totalPrice * promotion.getDiscountPercent() / 100;
            } else if (promotion.getDiscountAmount() != null) {
                totalPrice -= promotion.getDiscountAmount();
            }

            // Check avoid gia am (totalPrice < 0) va so luot da dung (userCount)
            if (totalPrice < 0) {
                totalPrice = 0;
            }

            promotion.setUsedCount(promotion.getUsedCount() + 1);
            promotionRepository.save(promotion);
        }

        // Tao booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowtime(showtime);
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.PENDING);
        booking.setPaymentStatus(PaymentStatus.UNPAID);

        booking = bookingRepository.save(booking);

        // Tao booking detail cho tung seat
        for (Seat seat : seats) {
            BookingDetail detail = new BookingDetail();
            detail.setBooking(booking);
            detail.setSeat(seat);
            detail.setPricePaid(baseTicketPrice + seat.getExtraPrice());

            bookingDetailRepository.save(detail);
        }

        // Tru available seats
        showtime.setAvailableSeats(
                showtime.getAvailableSeats() - seats.size()
        );
        showtimeRepository.save(showtime);

        return booking;
    }

    @Override
    public List<Booking> getUserBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bookingRepository.findByUserOrderByCreatedAtDesc(user);
    }
}
