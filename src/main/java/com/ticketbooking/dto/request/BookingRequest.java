package com.ticketbooking.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class BookingRequest {

    private List<Long> showSeatIds;

}