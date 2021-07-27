package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.port.out.BookingWithTable;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;

import java.util.List;

public class FindBookingsJdbiAdapter implements FindBookingsPort {
    @Override
    public List<BookingWithTable> findBookings() {
        return null;
    }
}
