package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class FindFreeTablesJdbiAdapter implements FindFreeTablesPort {

    private final Jdbi db;

    @Override
    public List<Table> find(Instant from, Instant to) {
        return null;
    }
}
