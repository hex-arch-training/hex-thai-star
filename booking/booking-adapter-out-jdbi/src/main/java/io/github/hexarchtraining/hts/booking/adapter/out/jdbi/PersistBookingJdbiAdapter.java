package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.BookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.port.out.PersistBookingPort;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jdbi.v3.core.Jdbi;

import java.util.HashSet;

@AllArgsConstructor
public class PersistBookingJdbiAdapter implements PersistBookingPort {

    private final Jdbi db;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    @Override
    public Booking persist(@NonNull Booking booking) {
        return db.withHandle(handle -> {
            final BookingDao bookingDao = handle.attach(BookingDao.class);
            final long id = bookingDao.insertBooking(bookingMapper.toRecord(booking));
            return bookingDao
                    .findBookingById(id)
                    .map((BookingRecord bookingRecord) -> bookingMapper.toDomain(bookingRecord, new HashSet<>()))
                    .orElseThrow(() -> new BookingNotFoundException(new BookingId(id)));
        });
    }
}
