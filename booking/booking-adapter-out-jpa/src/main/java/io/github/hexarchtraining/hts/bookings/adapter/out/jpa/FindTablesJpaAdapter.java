package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import io.github.hexarchtraining.hts.bookings.domain.Table;
import io.github.hexarchtraining.hts.bookings.port.out.FindTablesPort;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;

import java.util.List;

@AllArgsConstructor
public class FindTablesJpaAdapter implements FindTablesPort {

    private final TableRepository tableRepository;

    private final TableMapper tableMapper = TableMapper.INSTANCE;

    @Override
    public List<Table> findTables() {
        return Streamable.of(tableRepository.findAll()).map(tableMapper::toDomain).toList();
    }
}
