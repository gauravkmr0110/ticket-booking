package com.ticketbooking.util;

public class RedisKeyUtil {

    private RedisKeyUtil() {}

    public static String seatLockKey(Long showSeatId) {
        return "lock:showSeat:" + showSeatId;
    }
}