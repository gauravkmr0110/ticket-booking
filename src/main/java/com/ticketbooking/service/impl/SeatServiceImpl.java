package com.ticketbooking.service.impl;

import com.ticketbooking.dto.request.SeatRequest;
import com.ticketbooking.dto.response.SeatAvailabilityResponse;
import com.ticketbooking.entity.Seat;
import com.ticketbooking.entity.ShowSeat;
import com.ticketbooking.entity.Venue;
import com.ticketbooking.entity.enums.SeatStatus;
import com.ticketbooking.repository.SeatRepository;
import com.ticketbooking.repository.ShowSeatRepository;
import com.ticketbooking.repository.VenueRepository;
import com.ticketbooking.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private SeatRepository seatRepository;
    private VenueRepository venueRepository;
    private ShowSeatRepository showSeatRepository;

    @Override
    public Seat createSeat(Long venueId, SeatRequest request){

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        Seat seat = Seat.builder()
                .seatNumber(request.getSeatNumber())
                .venue(venue)
                .build();
        return seatRepository.save(seat);
    }

    @Override
    public List<SeatAvailabilityResponse> getAvailableSeats(Long showId){
        List<ShowSeat> availableSeats = showSeatRepository.findByShowIdAndStatus(
                showId,
                SeatStatus.AVAILABLE
        );

        return availableSeats.stream()
                .map(seat -> new SeatAvailabilityResponse(seat.getId(),
                        seat.getSeat().getSeatNumber(), seat.getPrice()))
                .toList();

    }



}
