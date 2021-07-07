package io.github.hexarchtraining.hts.application.bookings.port.out;

public interface SendBookingConfirmationPort {

    void send(BookingConfimationEvent event);
}
