package com.ticketbooking.controller;

import com.ticketbooking.dto.request.SeatRequest;
import com.ticketbooking.entity.Seat;
import com.ticketbooking.service.SeatService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues/{venueId}/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @PostMapping

    public ResponseEntity<Seat> createSeat(@PathVariable Long venueId,
                                           @RequestBody SeatRequest request){
        return ResponseEntity.ok(
                seatService.createSeat(venueId,request));
    }

}
