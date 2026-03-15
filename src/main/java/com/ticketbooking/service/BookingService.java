package com.ticketbooking.service;

import com.ticketbooking.dto.request.BookingRequest;
import com.ticketbooking.dto.response.BookingResponse;

public interface BookingService {

    BookingResponse createBooking(Long userId, BookingRequest request);
}
