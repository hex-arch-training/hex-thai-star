package io.github.hexarchtraining.hts.application.bookings.port.out;

import io.github.hexarchtraining.hts.domain.bookings.Table;

import java.time.Instant;
import java.util.List;

/**
 * Finds free (unbooked) tables for given time period.
 */
public interface FindFreeTablesPort {
    List<Table> find(Instant from, Instant to);
}
