package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingWithTableRecord {
    private BookingRecord booking;
    private TableBookingRecord tableBooking;
}
