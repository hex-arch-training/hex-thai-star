package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableBookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.port.out.BookingWithTable;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FindBookingsJdbiAdapter implements FindBookingsPort {

    private final Jdbi db;

    private final TableBookingMapper tableBookingMapper = TableBookingMapper.INSTANCE;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    @Override
    public List<BookingWithTable> findBookings() {

        return db.withHandle(handle -> {
            final TableBookingDao dao = handle.attach(TableBookingDao.class);
            return dao.findAllTableBookingsWithBooking().entrySet();
        }).stream().map(entry -> new BookingWithTable(
                bookingMapper.toDomain(entry.getValue()),
                Optional.ofNullable(tableBookingMapper.toDomain(entry.getKey()))
        )).collect(Collectors.toList());
    }
}
