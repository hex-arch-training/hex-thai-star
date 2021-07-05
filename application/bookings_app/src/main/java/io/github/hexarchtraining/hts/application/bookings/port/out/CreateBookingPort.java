package io.github.hexarchtraining.hts.application.bookings.port.out;

import io.github.hexarchtraining.hts.domain.bookings.Booking;

public interface CreateBookingPort {

    void createBooking(Booking booking);
}
