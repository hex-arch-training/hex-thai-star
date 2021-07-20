package io.github.hexarchtraining.hts.booking.port.out;

import io.github.hexarchtraining.hts.booking.domain.TableBooking;

import java.util.List;

public interface FindBookingsPort {
    List<TableBooking> findBookings();
}
