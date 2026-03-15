package com.ticketbooking.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class SeatLockRequest {

    private List<Long> showSeatIds;
}