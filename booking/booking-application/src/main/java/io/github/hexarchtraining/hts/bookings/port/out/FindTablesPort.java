package io.github.hexarchtraining.hts.bookings.port.out;

import io.github.hexarchtraining.hts.bookings.domain.Table;

import java.util.List;

public interface FindTablesPort {
    List<Table> findTables();
}
