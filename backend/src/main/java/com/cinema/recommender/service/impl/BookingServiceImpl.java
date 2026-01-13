package com.cinema.recommender.service.impl;

import com.cinema.recommender.dto.CreateBookingRequest;
import com.cinema.recommender.entity.*;
import com.cinema.recommender.entity.enums.BookingStatus;
import com.cinema.recommender.entity.enums.PaymentStatus;
import com.cinema.recommender.exception.NotFoundException;
import com.cinema.recommender.exception.SeatAlreadyBookedException;
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

    @Transactional
    @Override
    public Booking createBooking(CreateBookingRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Showtime showtime = showtimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new NotFoundException("Showtime not found"));

        // 1️⃣ Load seats
        List<Seat> seats = seatRepository.findAllById(request.getSeatIds());

        if (seats.size() != request.getSeatIds().size()) {
            throw new NotFoundException("One or more seats not found");
        }

        // 2️⃣ Check seat already booked
        for (Seat seat : seats) {
            boolean alreadyBooked =
                    bookingDetailRepository.existsBySeat_IdAndBooking_Showtime_Id(
                            seat.getId(),
                            showtime.getId()
                    );

            if (alreadyBooked) {
                throw new SeatAlreadyBookedException(
                        "Seat " + seat.getRowLetter() + seat.getNumber() + " already booked"
                );
            }
        }

        // 3️⃣ Xác định giá vé
        int baseTicketPrice;
        switch (showtime.getShowDate().getDayOfWeek()) {
            case SATURDAY, SUNDAY -> baseTicketPrice = showtime.getWeekendPrice();
            default -> baseTicketPrice = showtime.getBasePrice();
        }

        int totalPrice = seats.stream()
                .mapToInt(seat -> baseTicketPrice + seat.getExtraPrice())
                .sum();

        // 4️⃣ Apply promotion
        if (request.getPromotionCode() != null && !request.getPromotionCode().isBlank()) {
            Promotion promotion = promotionRepository
                    .findByCodeAndIsActiveTrue(request.getPromotionCode())
                    .orElseThrow(() -> new RuntimeException("Invalid promotion code"));

            if (promotion.getDiscountPercent() != null) {
                totalPrice -= totalPrice * promotion.getDiscountPercent() / 100;
            } else if (promotion.getDiscountAmount() != null) {
                totalPrice -= promotion.getDiscountAmount();
            }

            if (totalPrice < 0) totalPrice = 0;

            promotion.setUsedCount(promotion.getUsedCount() + 1);
            promotionRepository.save(promotion);
        }

        // 5️⃣ Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowtime(showtime);
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.PENDING);
        booking.setPaymentStatus(PaymentStatus.UNPAID);

        booking = bookingRepository.save(booking);

        // 6️⃣ Create booking details
        for (Seat seat : seats) {
            BookingDetail detail = new BookingDetail();
            detail.setBooking(booking);
            detail.setSeat(seat);
            detail.setPricePaid(baseTicketPrice + seat.getExtraPrice());

            bookingDetailRepository.save(detail);
        }

        // 7️⃣ Update available seats
        showtime.setAvailableSeats(showtime.getAvailableSeats() - seats.size());
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
