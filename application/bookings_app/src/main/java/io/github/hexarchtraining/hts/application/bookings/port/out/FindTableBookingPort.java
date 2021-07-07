package io.github.hexarchtraining.hts.application.bookings.port.out;

import io.github.hexarchtraining.hts.domain.bookings.BookingId;
import io.github.hexarchtraining.hts.domain.bookings.TableBooking;

import java.util.Optional;

public interface FindTableBookingPort {
    Optional<TableBooking> find(BookingId id);
}
