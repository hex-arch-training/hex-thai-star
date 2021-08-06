package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.BookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FindBookingsJdbiAdapter implements FindBookingsPort {

    private final Jdbi db;

    private final TableBookingMapper tableBookingMapper = TableBookingMapper.INSTANCE;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    @Override
    public List<Booking> findBookings() {

        return db.withHandle(handle -> {
            final BookingDao bookingDao = handle.attach(BookingDao.class);
            final Map<Long, BookingWithTables> joined = new HashMap<>();
            bookingDao.findAllBookingsWithTableBookingsAsStream().forEach(joinRow -> {
                final BookingRecord bookingRecord = joinRow.get(BookingRecord.class);
                final TableBookingRecord tableBookingRecord = joinRow.get(TableBookingRecord.class);
                final BookingWithTables bookingWithTables = joined.computeIfAbsent(bookingRecord.getId(), (id) -> new BookingWithTables(bookingRecord));
                if (tableBookingRecord.getId() != null) {
                    bookingWithTables.add(tableBookingRecord);
                }
            });
            return joined.values();
        }).stream().map(entry -> {
            final Set<TableBooking> tableBookings = entry.getTableBookings().stream()
                    .map(tableBookingMapper::toDomain)
                    .collect(Collectors.toSet());
            return bookingMapper.toDomain(entry.getBooking(), tableBookings);
        }).collect(Collectors.toList());
    }
}
