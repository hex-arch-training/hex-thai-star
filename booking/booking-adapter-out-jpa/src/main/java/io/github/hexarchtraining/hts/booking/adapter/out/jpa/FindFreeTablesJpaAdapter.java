package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.TableMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableBookingRepository;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableRepository;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FindFreeTablesJpaAdapter implements FindFreeTablesPort {

    private final TableRepository tableRepository;

    private final TableBookingRepository tableBookingRepository;

    private final TableMapper tableMapper = TableMapper.INSTANCE;

    @Override
    public List<Table> find(Instant from, Instant to) {
        final Set<Long> bookedTableIds = tableBookingRepository.findBookingsIntersect(from, to).stream().map(entity -> entity.getTable().getId()).collect(Collectors.toSet());
        return Streamable.of(tableRepository.findAll())
                .filter(entity -> !bookedTableIds.contains(entity.getId()))
                .map(tableMapper::toDomain)
                .toList();
    }
}
