package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.BookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableBookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.TableId;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SaveBookingJdbiAdapter implements SaveBookingPort {

    private final Jdbi db;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    private final TableBookingMapper tableBookingMapper = TableBookingMapper.INSTANCE;

    @Override
    public void save(@NonNull Booking booking) {
        db.useHandle(handle -> {
            final BookingDao bookingDao = handle.attach(BookingDao.class);
            final TableBookingDao tableBookingDao = handle.attach(TableBookingDao.class);
            // update booking
            final boolean updated = bookingDao.updateBooking(bookingMapper.toRecord(booking));
            if (!updated) {
                throw new BookingNotFoundException(booking.getId());
            }
            // update table bookings
            final List<TableBookingRecord> tableBookings = tableBookingDao.findAllTableBookingsForBooking(booking.getId().getValue());
            tableBookings.forEach(tableBookingRecord -> booking.findTable(new TableId(tableBookingRecord.getTableId()))
                    .ifPresentOrElse(tableBooking -> {
                        final TableBookingRecord changed = tableBookingMapper.toRecord(tableBooking);
                        changed.setId(tableBookingRecord.getId());
                        mapFromBooking(booking, changed);
                        changed.setTableId(tableBookingRecord.getTableId());

                        if (!changed.equals(tableBookingRecord)) {
                            tableBookingDao.updateTableBooking(changed);
                        }
                    }, () -> tableBookingDao.deleteTableBooking(
                            tableBookingRecord.getBookingId(),
                            tableBookingRecord.getTableId())));

            // insert new
            final Set<TableId> existingBookingEntities = tableBookings.stream()
                    .map(TableBookingRecord::getTableId)
                    .map(TableId::new)
                    .collect(Collectors.toSet());

            final Set<TableId> newBookings = booking.bookingsAsTableIdStream()
                    .filter(existingBookingEntities::contains)
                    .collect(Collectors.toSet());

            newBookings.forEach(tableId -> booking.findTable(tableId).ifPresent(tableBooking -> {
                final TableBookingRecord record = tableBookingMapper.toRecord(tableBooking);
                mapFromBooking(booking, record);
                record.setTableId(tableId.getValue());
                tableBookingDao.insertTableBooking(record);
            }));
        });
    }


    private void mapFromBooking(Booking booking, TableBookingRecord record) {
        record.setBookingId(booking.getId().getValue());
        record.setBookingFrom(booking.getBookingFromTime());
        record.setBookingTo(booking.getBookingToTime());
    }
}
