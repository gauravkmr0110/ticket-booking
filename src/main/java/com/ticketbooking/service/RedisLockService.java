package com.ticketbooking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisLockService {

    private final StringRedisTemplate redisTemplate;

    public boolean lockSeat(String key, String userId, long ttlSeconds) {

        Boolean success = redisTemplate
                .opsForValue()
                .setIfAbsent(key, userId, Duration.ofSeconds(ttlSeconds));

        return Boolean.TRUE.equals(success);
    }

    public String getLockOwner(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void releaseLock(String key) {
        redisTemplate.delete(key);
    }
}