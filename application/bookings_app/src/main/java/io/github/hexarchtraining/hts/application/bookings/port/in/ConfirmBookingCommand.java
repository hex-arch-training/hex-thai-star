package io.github.hexarchtraining.hts.application.bookings.port.in;

import lombok.Value;

@Value
public class ConfirmBookingCommand {

    String token;
}
