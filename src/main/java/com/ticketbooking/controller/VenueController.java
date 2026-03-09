package com.ticketbooking.controller;

import com.ticketbooking.dto.request.VenueRequest;
import com.ticketbooking.entity.Venue;
import com.ticketbooking.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    public ResponseEntity<Venue> createVenue(@RequestBody VenueRequest request){

        return ResponseEntity.ok(
                venueService.createVenue(request));
    }
}
