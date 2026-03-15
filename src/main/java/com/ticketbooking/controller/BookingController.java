package com.ticketbooking.controller;

import com.ticketbooking.dto.request.BookingRequest;
import com.ticketbooking.dto.response.BookingResponse;
import com.ticketbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse>createBooking(@RequestBody BookingRequest request){
        Long userId = 1L; // temporary until security added

        return ResponseEntity.ok(
                bookingService.createBooking(userId, request)
        );
    }
}
