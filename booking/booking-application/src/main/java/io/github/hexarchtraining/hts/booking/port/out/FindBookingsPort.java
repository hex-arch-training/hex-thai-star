package io.github.hexarchtraining.hts.booking.port.out;

import java.util.List;

public interface FindBookingsPort {
    List<BookingWithTable> findBookings();
}
