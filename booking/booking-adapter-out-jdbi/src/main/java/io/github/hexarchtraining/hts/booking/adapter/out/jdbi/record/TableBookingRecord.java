package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableBookingRecord {
    private Long id;
    private Instant bookingFrom;
    private Instant bookingTo;
    private int seatsNumber;
    private long tableId;
    private long bookingId;
}
