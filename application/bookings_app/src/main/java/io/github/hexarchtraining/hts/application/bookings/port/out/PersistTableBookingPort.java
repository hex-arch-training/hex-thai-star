package io.github.hexarchtraining.hts.application.bookings.port.out;

import io.github.hexarchtraining.hts.domain.bookings.TableBooking;

public interface PersistTableBookingPort {

    void persist(TableBooking tableBooking);
}
