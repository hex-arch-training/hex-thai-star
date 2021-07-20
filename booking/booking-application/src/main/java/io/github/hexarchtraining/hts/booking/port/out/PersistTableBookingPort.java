package io.github.hexarchtraining.hts.booking.port.out;

import io.github.hexarchtraining.hts.booking.domain.TableBooking;

public interface PersistTableBookingPort {
    void persist(TableBooking tableBooking);
}
