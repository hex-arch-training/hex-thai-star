package io.github.hexarchtraining.hts.booking.port.in;

public interface CancelBookingPort {
    void cancel(CancelBookingCommand command);
}
