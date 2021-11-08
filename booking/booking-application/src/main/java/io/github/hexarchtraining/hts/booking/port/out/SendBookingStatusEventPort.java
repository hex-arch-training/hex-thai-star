package io.github.hexarchtraining.hts.booking.port.out;

public interface SendBookingStatusEventPort {
    void send(BookingStatusEvent event);
}
