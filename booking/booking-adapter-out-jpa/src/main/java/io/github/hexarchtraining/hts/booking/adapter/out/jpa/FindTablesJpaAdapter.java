package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.TableMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableRepository;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.port.out.FindTablesPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FindTablesJpaAdapter implements FindTablesPort {

    private final TableRepository tableRepository;

    private final TableMapper tableMapper = TableMapper.INSTANCE;

    @Override
    public List<Table> findTables() {
        return tableRepository.findAll()
            .stream()
            .map(tableMapper::toDomain)
            .collect(Collectors.toList());
    }
}
