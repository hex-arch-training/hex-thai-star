package io.github.hexarchtraining.hts.booking.port.out;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import lombok.Value;

import java.util.Optional;

@Value
public class BookingWithTable {

    Booking booking;

    Optional<TableBooking> tableBooking;
}
