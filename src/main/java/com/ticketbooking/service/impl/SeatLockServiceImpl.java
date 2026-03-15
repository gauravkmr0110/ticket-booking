package com.ticketbooking.service.impl;

import com.ticketbooking.dto.response.SeatLockResponse;
import com.ticketbooking.service.RedisLockService;
import com.ticketbooking.service.SeatLockService;
import com.ticketbooking.util.RedisKeyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatLockServiceImpl implements SeatLockService {

    private final RedisLockService redisLockService;

    private static final long LOCK_TTL_SECONDS = 300;

    @Override
    public List<SeatLockResponse> lockSeats(Long showId, List<Long>showSeatIds, String userId){

        List<SeatLockResponse> responses = new ArrayList<>();

        for(Long showSeatId : showSeatIds){

            String key = RedisKeyUtil.seatLockKey(showSeatId);

            boolean locked = redisLockService.lockSeat(key, userId, LOCK_TTL_SECONDS);

            responses.add(
                    SeatLockResponse.builder()
                            .showSeatId(showSeatId)
                            .locked(locked)
                            .build()
            );
        }

        return responses;

    }
}
