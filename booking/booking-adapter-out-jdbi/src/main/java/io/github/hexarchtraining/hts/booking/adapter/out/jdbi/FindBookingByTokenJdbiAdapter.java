package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.BookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jdbi.v3.core.Jdbi;

import java.util.Optional;

@AllArgsConstructor
public class FindBookingByTokenJdbiAdapter implements FindBookingByTokenPort {

    private final Jdbi db;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    @Override
    public Optional<Booking> find(@NonNull String token) {
        return db.withHandle(handle -> {
            final BookingDao dao = handle.attach(BookingDao.class);
            return dao.findBookingByToken(token);
        }).map(bookingMapper::toDomain);
    }
}
