package io.github.hexarchtraining.hts.booking.port.out;

import io.github.hexarchtraining.hts.booking.domain.Booking;

import java.util.List;

public interface FindBookingsPort {
    List<Booking> findBookings();
}
