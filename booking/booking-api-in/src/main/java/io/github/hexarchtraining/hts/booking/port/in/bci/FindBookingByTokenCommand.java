package io.github.hexarchtraining.hts.booking.port.in.bci;

import lombok.Value;

@Value
public class FindBookingByTokenCommand {
    String token;
}
