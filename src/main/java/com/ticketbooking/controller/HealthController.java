package com.ticketbooking.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Operation(summary = "Health check API")
    @GetMapping("/health")
    public String health() {
        return "Ticket Booking Service is UP";
    }
}
