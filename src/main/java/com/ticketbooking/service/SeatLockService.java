package com.ticketbooking.service;


import com.ticketbooking.dto.response.SeatLockResponse;

import java.util.List;

public interface SeatLockService {

    List<SeatLockResponse> lockSeats(Long showId, List<Long> showSeatIds, String userId);

}

