package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;


import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableBookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.port.out.PersistTableBookingPort;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jdbi.v3.core.Jdbi;

@AllArgsConstructor
public class PersistTableBookingJdbiAdapter implements PersistTableBookingPort {

    private final Jdbi db;

    private final TableBookingMapper tableBookingMapper = TableBookingMapper.INSTANCE;

    @Override
    public void persist(@NonNull TableBooking tableBooking) {
        db.useHandle(handle -> {
            final TableBookingDao dao = handle.attach(TableBookingDao.class);
            dao.insertTableBooking(tableBookingMapper.toRecord(tableBooking));
        });
    }
}
