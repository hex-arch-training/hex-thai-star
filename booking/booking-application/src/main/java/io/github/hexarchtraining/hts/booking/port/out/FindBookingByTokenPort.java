package io.github.hexarchtraining.hts.booking.port.out;

import io.github.hexarchtraining.hts.booking.domain.Booking;

import java.util.Optional;

public interface FindBookingByTokenPort {
    Optional<Booking> find(String token);
}
