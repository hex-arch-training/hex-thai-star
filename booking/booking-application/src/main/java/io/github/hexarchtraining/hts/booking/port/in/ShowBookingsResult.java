package io.github.hexarchtraining.hts.booking.port.in;

import lombok.Value;

import java.time.Instant;

@Value
public class ShowBookingsResult {

    String email;

    Instant bookingFrom;

    Instant bookingTo;

    Long tableId;

    int maxSeats;

    String token;

    String status;
}
