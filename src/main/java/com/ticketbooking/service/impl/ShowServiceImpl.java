package com.ticketbooking.service.impl;

import com.ticketbooking.dto.request.ShowRequest;
import com.ticketbooking.entity.Seat;
import com.ticketbooking.entity.Show;
import com.ticketbooking.entity.ShowSeat;
import com.ticketbooking.entity.Venue;
import com.ticketbooking.entity.enums.SeatStatus;
import com.ticketbooking.repository.SeatRepository;
import com.ticketbooking.repository.ShowRepository;
import com.ticketbooking.repository.ShowSeatRepository;
import com.ticketbooking.repository.VenueRepository;
import com.ticketbooking.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;
    private final VenueRepository venueRepository;
    private final  SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;

    @Override
    public Show createShow(Long venueId, ShowRequest request){
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        Show show = Show.builder()
                .eventName(request.getEventName())
                .startTime(request.getStartTime())
                .venue(venue)
                .build();

        show = showRepository.save(show);

        List<Seat> seats = seatRepository.findByVenueId(venueId);

        for(Seat seat: seats){
            ShowSeat showSeat = ShowSeat.builder()
                    .show(show)
                    .seat(seat)
                    .status(SeatStatus.AVAILABLE)
                    .price(200)
                    .build();

            showSeatRepository.save(showSeat);
        }

        return show;
    }
}
