package io.github.hexarchtraining.hts.booking.port.in;

import lombok.Value;

@Value
public class ConfirmBookingCommand {

    String token;
}
