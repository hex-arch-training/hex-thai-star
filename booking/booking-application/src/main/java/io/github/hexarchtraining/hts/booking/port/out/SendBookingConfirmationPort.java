package io.github.hexarchtraining.hts.booking.port.out;

public interface SendBookingConfirmationPort {
    void send(BookingConfimationEvent event);
}
