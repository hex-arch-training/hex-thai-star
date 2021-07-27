package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.port.out.BookingWithTable;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

@AllArgsConstructor
public class FindBookingsJdbiAdapter implements FindBookingsPort {

    private final Jdbi db;

    @Override
    public List<BookingWithTable> findBookings() {
        return null;
    }
}
