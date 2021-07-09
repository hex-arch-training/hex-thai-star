package io.github.hexarchtraining.hts.bookings.port.out;

import io.github.hexarchtraining.hts.bookings.domain.Booking;

public interface SaveBookingPort {

    void save(Booking booking);
}
