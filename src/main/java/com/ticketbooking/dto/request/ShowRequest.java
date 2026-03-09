package com.ticketbooking.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowRequest {
    private String eventName;
    private LocalDateTime startTime;
}
