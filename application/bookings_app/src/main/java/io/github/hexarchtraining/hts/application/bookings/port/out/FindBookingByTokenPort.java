package io.github.hexarchtraining.hts.application.bookings.port.out;

import io.github.hexarchtraining.hts.domain.bookings.Booking;

import java.util.Optional;

public interface FindBookingByTokenPort {
    Optional<Booking> find(String token);
}
