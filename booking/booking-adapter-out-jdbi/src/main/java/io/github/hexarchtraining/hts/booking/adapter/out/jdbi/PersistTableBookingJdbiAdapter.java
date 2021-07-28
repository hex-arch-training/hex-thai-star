package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;


import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.port.out.PersistTableBookingPort;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

@AllArgsConstructor
public class PersistTableBookingJdbiAdapter implements PersistTableBookingPort {

    private final Jdbi db;

    @Override
    public void persist(TableBooking tableBooking) {

    }
}
