package com.ticketbooking.controller;

import com.ticketbooking.dto.request.SeatLockRequest;
import com.ticketbooking.dto.response.SeatLockResponse;
import com.ticketbooking.service.SeatLockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
public class SeatLockController {

    private final SeatLockService seatLockService;

    @PostMapping("/{showId}/lock")
    public ResponseEntity<List<SeatLockResponse>> lockSeats(
            @PathVariable Long showId,
            @RequestBody SeatLockRequest request) {

        String userId = "user123"; // temporary (auth later)

        return ResponseEntity.ok(
                seatLockService.lockSeats(
                        showId,
                        request.getShowSeatIds(),
                        userId
                )
        );
    }
}