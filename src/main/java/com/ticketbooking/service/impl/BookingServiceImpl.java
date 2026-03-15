package com.ticketbooking.service.impl;

import com.ticketbooking.dto.request.BookingRequest;
import com.ticketbooking.dto.response.BookingResponse;
import com.ticketbooking.entity.Booking;
import com.ticketbooking.entity.BookingSeat;
import com.ticketbooking.entity.ShowSeat;
import com.ticketbooking.entity.User;
import com.ticketbooking.entity.enums.BookingStatus;
import com.ticketbooking.entity.enums.SeatStatus;
import com.ticketbooking.repository.BookingRepository;
import com.ticketbooking.repository.BookingSeatRepository;
import com.ticketbooking.repository.ShowSeatRepository;
import com.ticketbooking.repository.UserRepository;
import com.ticketbooking.service.BookingService;
import com.ticketbooking.service.RedisLockService;
import com.ticketbooking.util.RedisKeyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final ShowSeatRepository showSeatRepository;
    private final RedisLockService redisLockService;
    private  final UserRepository userRepository;

    @Transactional
    @Override
    public BookingResponse createBooking(Long userId, BookingRequest request){

        List<ShowSeat>seats  = showSeatRepository.findAllById(request.getShowSeatIds());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = Booking.builder()
                .user(user)
                .status(BookingStatus.CONFIRMED)
                .build();
        booking = bookingRepository.save(booking);

        List<BookingSeat>bookingSeats  = new ArrayList<>();

        for(ShowSeat seat: seats){

            String lockKey = RedisKeyUtil.seatLockKey(seat.getId());
            String lockOwner = redisLockService.getLockOwner(lockKey);

            if(lockOwner == null || !lockOwner.equals(String.valueOf(userId))){
                throw new RuntimeException("Seat not locked by this user");
            }

            seat.setStatus(SeatStatus.BOOKED);

            BookingSeat bookingSeat = BookingSeat.builder()
                    .booking(booking)
                    .showSeat(seat)
                    .build();
            bookingSeats.add(bookingSeat);
            redisLockService.releaseLock(lockKey);
        }
        showSeatRepository.saveAll(seats);
        bookingSeatRepository.saveAll(bookingSeats);

        return BookingResponse.builder()
                .bookingId(booking.getId())
                .status("CONFIRMED")
                .build();

    }
}
