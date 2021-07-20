package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import io.github.hexarchtraining.hts.bookings.domain.Table;
import io.github.hexarchtraining.hts.bookings.port.out.FindFreeTablesPort;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class FindFreeTablesJpaAdapter implements FindFreeTablesPort {

    private final TableRepository tableRepository;

    private final TableMapper tableMapper = TableMapper.INSTANCE;

    @Override
    public List<Table> find(Instant from, Instant to) {
        return null;
    }
}
