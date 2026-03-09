package com.ticketbooking.repository;

import com.ticketbooking.entity.ShowSeat;
import com.ticketbooking.entity.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    List<ShowSeat> findByShowIdAndStatus(Long showId, SeatStatus status);

    Optional<ShowSeat> findByShowIdAndSeatId(Long showId, Long seatId);

    List<ShowSeat> findByShowId(Long showId);
}
