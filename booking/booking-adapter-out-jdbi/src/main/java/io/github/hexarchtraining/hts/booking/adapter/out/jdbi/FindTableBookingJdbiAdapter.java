package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableBookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.port.out.FindTableBookingPort;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import java.util.Optional;

@AllArgsConstructor
public class FindTableBookingJdbiAdapter implements FindTableBookingPort {

    private final Jdbi db;

    private final TableBookingMapper tableBookingMapper = TableBookingMapper.INSTANCE;

    @Override
    public Optional<TableBooking> find(BookingId id) {
        return db.withHandle(handle -> {
            final TableBookingDao dao = handle.attach(TableBookingDao.class);
            return dao.findTableBooking(id.getValue());
        }).map(tableBookingMapper::toDomain);
    }
}
