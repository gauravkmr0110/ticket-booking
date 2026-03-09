package com.ticketbooking.service;

import com.ticketbooking.dto.request.ShowRequest;
import com.ticketbooking.entity.Show;

public interface ShowService {

    Show createShow(Long venueId, ShowRequest request);
}
