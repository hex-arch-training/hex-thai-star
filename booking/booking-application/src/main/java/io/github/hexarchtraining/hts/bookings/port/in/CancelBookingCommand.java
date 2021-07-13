package io.github.hexarchtraining.hts.bookings.port.in;

import lombok.Value;

@Value
public class CancelBookingCommand {

    String token;
}
