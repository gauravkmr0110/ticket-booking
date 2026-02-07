package com.ticketbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(
        name = "booking_seats",
        indexes = {
                @Index(name = "idx_booking_id", columnList = "booking_id")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ShowSeat showSeat;
}
