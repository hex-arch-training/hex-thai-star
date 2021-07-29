package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableBookingDao;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.port.out.DeleteTableBookingPort;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jdbi.v3.core.Jdbi;

@AllArgsConstructor
public class DeleteTableBookingJdbiAdapter implements DeleteTableBookingPort {

    private final Jdbi db;

    @Override
    public void delete(@NonNull TableBooking tableBooking) {
        db.useHandle(handle -> {
            final TableBookingDao dao = handle.attach(TableBookingDao.class);
            dao.deleteTableBooking(
                    tableBooking.getBookingId().getValue(),
                    tableBooking.getTableId().getValue());
        });
    }
}
