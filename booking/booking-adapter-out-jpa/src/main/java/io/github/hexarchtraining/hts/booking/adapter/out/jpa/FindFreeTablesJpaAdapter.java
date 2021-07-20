package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.TableMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableRepository;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
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
