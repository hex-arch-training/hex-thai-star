package io.github.hexarchtraining.hts.bookings.port.out;

import io.github.hexarchtraining.hts.bookings.domain.BookingId;
import io.github.hexarchtraining.hts.bookings.domain.TableBooking;

import java.util.Optional;

public interface FindTableBookingPort {
    Optional<TableBooking> find(BookingId id);
}
