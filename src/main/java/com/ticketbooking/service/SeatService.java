package com.ticketbooking.service;

import com.ticketbooking.dto.request.SeatRequest;
import com.ticketbooking.dto.response.SeatAvailabilityResponse;
import com.ticketbooking.entity.Seat;

import java.util.List;

public interface SeatService {
    Seat createSeat(Long venueId, SeatRequest request);
    List<SeatAvailabilityResponse> getAvailableSeats(Long showId);
}
