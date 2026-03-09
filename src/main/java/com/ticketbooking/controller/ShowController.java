package com.ticketbooking.controller;

import com.ticketbooking.dto.request.ShowRequest;
import com.ticketbooking.dto.response.SeatAvailabilityResponse;
import com.ticketbooking.entity.Show;
import com.ticketbooking.service.SeatService;
import com.ticketbooking.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues/{venueId}/shows")
@RequiredArgsConstructor
public class ShowController {
    private  final ShowService showService;
    private final SeatService seatService;

    @PostMapping
    public ResponseEntity<Show> createShow(@PathVariable Long venueId,
                                           @RequestBody ShowRequest request){
        return ResponseEntity.ok(
                showService.createShow(venueId,request));
    }
    @GetMapping("{showId}/seats/available")
    public ResponseEntity<List<SeatAvailabilityResponse>> getAvailableSeats(@PathVariable Long venueId, @PathVariable Long showId){
        return ResponseEntity.ok(
                seatService.getAvailableSeats(showId)
        );
    }
}
