package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.BookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableBookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FindBookingByTokenJdbiAdapter implements FindBookingByTokenPort {

    private final Jdbi db;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    private final TableBookingMapper tableBookingMapper = TableBookingMapper.INSTANCE;

    @Override
    public Optional<Booking> find(@NonNull String token) {
        return db.withHandle(handle -> {
            final BookingDao dao = handle.attach(BookingDao.class);
            final TableBookingDao tableBookingDao = handle.attach(TableBookingDao.class);
            return dao.findBookingByToken(token).map(bookingRecord -> {
                List<TableBookingRecord> tableBookings = tableBookingDao.findAllTableBookingsForBooking(bookingRecord.getId());
                return  bookingMapper.toDomain(
                        bookingRecord, tableBookings.stream()
                                .map(tableBookingMapper::toDomain).collect(Collectors.toSet())
                        );
            });
        });
    }
}
