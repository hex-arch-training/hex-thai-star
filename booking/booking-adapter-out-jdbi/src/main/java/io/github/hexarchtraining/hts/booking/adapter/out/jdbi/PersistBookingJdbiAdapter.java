package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.port.out.PersistBookingPort;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

@AllArgsConstructor
public class PersistBookingJdbiAdapter implements PersistBookingPort {

    private final Jdbi db;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    @Override
    public Booking persist(Booking booking) {
        return null;
    }
}
