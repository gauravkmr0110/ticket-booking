package com.ticketbooking.service;

import com.ticketbooking.dto.request.VenueRequest;
import com.ticketbooking.entity.Venue;

public interface VenueService {
    Venue createVenue(VenueRequest request);
}
