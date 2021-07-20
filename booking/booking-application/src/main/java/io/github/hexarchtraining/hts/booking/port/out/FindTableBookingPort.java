package io.github.hexarchtraining.hts.booking.port.out;

import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;

import java.util.Optional;

public interface FindTableBookingPort {
    Optional<TableBooking> find(BookingId id);
}
