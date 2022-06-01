package io.github.hexarchtraining.hts.booking.port.out;

import io.github.hexarchtraining.hts.booking.domain.Booking;

public interface SaveBookingPort {
    void save(Booking booking);
}
