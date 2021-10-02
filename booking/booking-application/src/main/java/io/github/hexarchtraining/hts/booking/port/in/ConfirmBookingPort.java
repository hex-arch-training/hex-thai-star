package io.github.hexarchtraining.hts.booking.port.in;

public interface ConfirmBookingPort {

    void confirm(ConfirmBookingCommand command);
}
