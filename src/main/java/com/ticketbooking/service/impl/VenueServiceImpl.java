package com.ticketbooking.service.impl;

import com.ticketbooking.dto.request.VenueRequest;
import com.ticketbooking.entity.Venue;
import com.ticketbooking.repository.VenueRepository;
import com.ticketbooking.service.VenueService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public Venue createVenue(VenueRequest request){
        Venue venue = Venue.builder()
                .name(request.getName())
                .city(request.getCity())
                .build();

        return venueRepository.save(venue);
    }
}
