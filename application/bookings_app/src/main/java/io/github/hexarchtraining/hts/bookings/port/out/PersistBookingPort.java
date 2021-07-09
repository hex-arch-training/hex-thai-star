package io.github.hexarchtraining.hts.bookings.port.out;

import io.github.hexarchtraining.hts.bookings.domain.Booking;

public interface PersistBookingPort {

    Booking persist(Booking booking);
}
