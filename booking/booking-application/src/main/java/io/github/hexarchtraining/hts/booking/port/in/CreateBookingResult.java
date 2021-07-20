package io.github.hexarchtraining.hts.booking.port.in;

import lombok.Value;

@Value
public class CreateBookingResult {
    long bookingId;
    Long tableId;
    String token;
}
