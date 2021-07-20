package io.github.hexarchtraining.hts.booking.port.out;

import io.github.hexarchtraining.hts.booking.domain.Table;

import java.time.Instant;
import java.util.List;

/**
 * Finds free (unbooked) tables for given time period.
 */
public interface FindFreeTablesPort {
    List<Table> find(Instant from, Instant to);
}
