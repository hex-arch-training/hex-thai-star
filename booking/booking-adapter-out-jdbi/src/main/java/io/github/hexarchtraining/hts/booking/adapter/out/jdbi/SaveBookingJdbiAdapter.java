package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.BookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jdbi.v3.core.Jdbi;

@AllArgsConstructor
public class SaveBookingJdbiAdapter implements SaveBookingPort {

    private final Jdbi db;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    @Override
    public void save(@NonNull Booking booking) {
        db.useHandle(handle -> {
            final BookingDao dao = handle.attach(BookingDao.class);
            final boolean updated = dao.updateBooking(bookingMapper.toRecord(booking));
            if (!updated) {
                throw new BookingNotFoundException(booking.getId());
            }
        });
    }
}
