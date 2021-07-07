package io.github.hexarchtraining.hts.application.bookings.port.out;

import io.github.hexarchtraining.hts.domain.bookings.TableBooking;

public interface DeleteTableBookingPort {
    void delete(TableBooking tableBooking);
}
