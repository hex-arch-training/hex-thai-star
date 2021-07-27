package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record;

import lombok.Value;

import java.time.Instant;

@Value
public class TableBookingRecord {
    Long id;
    Instant bookingFrom;
    Instant bookingTo;
    long tableId;
    long bookingId;
}
