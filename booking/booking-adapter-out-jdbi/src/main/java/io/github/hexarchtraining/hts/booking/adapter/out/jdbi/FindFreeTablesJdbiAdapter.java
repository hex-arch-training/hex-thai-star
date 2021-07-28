package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableBookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.TableMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jdbi.v3.core.Jdbi;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FindFreeTablesJdbiAdapter implements FindFreeTablesPort {

    private final Jdbi db;

    private final TableMapper tableMapper = TableMapper.INSTANCE;

    @Override
    public List<Table> find(@NonNull Instant from, @NonNull Instant to) {
        return db.withHandle(handle -> {
            final TableDao tableDao = handle.attach(TableDao.class);
            final TableBookingDao tableBookingDao = handle.attach(TableBookingDao.class);
            
            final Set<Long> bookedTableIds = tableBookingDao.findBookingsIntersect(from, to).stream()
                    .map(TableBookingRecord::getTableId)
                    .collect(Collectors.toSet());
            return tableDao.findAll()
                    .stream()
                    .filter(entity -> !bookedTableIds.contains(entity.getId()))
                    .map(tableMapper::toDomain)
                    .collect(Collectors.toList());
        });
    }
}
