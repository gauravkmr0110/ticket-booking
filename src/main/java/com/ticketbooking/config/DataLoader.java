package com.ticketbooking.config;
import com.ticketbooking.entity.Seat;
import com.ticketbooking.entity.Venue;
import com.ticketbooking.repository.SeatRepository;
import com.ticketbooking.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final VenueRepository venueRepository;
    private final SeatRepository seatRepository;

    private final Random random = new Random();

    @Override
    public void run(String... args) {

        if (venueRepository.count() > 0) {
            return;
        }

        List<Venue> venues = List.of(
                new Venue(null,"PVR Forum Mall","Bangalore"),
                new Venue(null,"INOX Megaplex","Mumbai"),
                new Venue(null,"IMAX Grand Cinema","Delhi"),
                new Venue(null,"Siri Fort Auditorium","Delhi"),
                new Venue(null,"Nehru Auditorium","Chennai"),
                new Venue(null,"Eden Gardens Stadium","Kolkata"),
                new Venue(null,"Wankhede Stadium","Mumbai"),
                new Venue(null,"M Chinnaswamy Stadium","Bangalore"),
                new Venue(null,"Narendra Modi Stadium","Ahmedabad"),
                new Venue(null,"Arun Jaitley Stadium","Delhi"),
                new Venue(null,"Shanmukhananda Auditorium","Mumbai"),
                new Venue(null,"Prithvi Theatre","Mumbai"),
                new Venue(null,"Tagore Theatre","Chandigarh"),
                new Venue(null,"Ravindra Bharathi Auditorium","Hyderabad"),
                new Venue(null,"Bharat Mandapam Hall","Delhi"),
                new Venue(null,"DY Patil Stadium","Navi Mumbai"),
                new Venue(null,"Greenfield Stadium","Thiruvananthapuram"),
                new Venue(null,"Rajiv Gandhi Stadium","Hyderabad"),
                new Venue(null,"Jawaharlal Nehru Stadium","Delhi"),
                new Venue(null,"Salt Lake Stadium","Kolkata")
        );

        for (Venue venue : venues) {

            venueRepository.save(venue);

            List<Seat> seats = new ArrayList<>();

            for (char row = 'A'; row <= 'J'; row++) {
                for (int col = 1; col <= 10; col++) {

                    Long price = (long)(random.nextInt(9) + 2) * 100;

                    Seat seat = Seat.builder()
                            .seatNumber(row + String.valueOf(col))
                            .price(price)
                            .venue(venue)
                            .build();

                    seats.add(seat);
                }
            }

            seatRepository.saveAll(seats);
        }

        System.out.println("Created 20 venues and 2000 seats successfully.");
    }
}