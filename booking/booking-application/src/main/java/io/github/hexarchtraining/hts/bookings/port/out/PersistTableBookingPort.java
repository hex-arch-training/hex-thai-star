package io.github.hexarchtraining.hts.bookings.port.out;

import io.github.hexarchtraining.hts.bookings.domain.TableBooking;

public interface PersistTableBookingPort {

    void persist(TableBooking tableBooking);
}
