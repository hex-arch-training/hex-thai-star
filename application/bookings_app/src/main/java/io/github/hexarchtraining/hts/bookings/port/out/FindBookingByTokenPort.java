package io.github.hexarchtraining.hts.bookings.port.out;

import io.github.hexarchtraining.hts.bookings.domain.Booking;

import java.util.Optional;

public interface FindBookingByTokenPort {
    Optional<Booking> find(String token);
}
