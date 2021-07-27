package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record;

import lombok.Value;

@Value
public class TableRecord {
    Long id;
    int maxSeats;
}
