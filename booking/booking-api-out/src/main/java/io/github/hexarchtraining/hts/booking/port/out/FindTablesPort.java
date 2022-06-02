package io.github.hexarchtraining.hts.booking.port.out;

import io.github.hexarchtraining.hts.booking.domain.Table;

import java.util.List;

public interface FindTablesPort {
    List<Table> findTables();
}
