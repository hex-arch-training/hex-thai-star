package io.github.hexarchtraining.hts.bookings.port.out;

public interface SendBookingConfirmationPort {
    void send(BookingConfimationEvent event);
}
