package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

@AllArgsConstructor
public class SaveBookingJdbiAdapter implements SaveBookingPort {

    private final Jdbi db;

    @Override
    public void save(Booking booking) {

    }
}
