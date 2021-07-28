package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TableBookingDao {

    @SqlUpdate("DELETE FROM Table_Booking WHERE booking_id=:bookingId AND table_id=:tableId")
    void deleteTableBooking(@Bind("bookingId") long bookingId, @Bind("tableId") long tableId);

    @SqlQuery("SELECT * FROM Table_Booking WHERE booking_id=:bookingId")
    @RegisterBeanMapper(TableBookingRecord.class)
    Optional<TableBookingRecord> findTableBooking(@Bind("bookingId") long bookingId);

    @SqlQuery("SELECT tb.*, b.* FROM Table_Booking tb JOIN Booking b ON tb.booking_id = b.id")
    @RegisterBeanMapper(value = TableBookingRecord.class, prefix = "tb")
    @RegisterBeanMapper(value = BookingRecord.class, prefix = "b")
    Map<TableBookingRecord, BookingRecord> findAllTableBookingsWithBooking();

    @SqlQuery("SELECT * FROM Table_Booking " +
            "WHERE (booking_from <= :fromTime AND booking_to >= :fromTime) " +
            "OR (booking_from <= :toTime AND booking_to >= :toTime) " +
            "OR (booking_from > :fromTime AND booking_to < :toTime)")
    List<TableBookingRecord> findBookingsIntersect(@Bind("fromTime") Instant from, @Bind("toTime") Instant to);

    @SqlUpdate("INSERT INTO Table_Booking (booking_from, booking_to, table_id, booking_id) " +
            "VALUES (tableBooking.bookingFrom, tableBooking.bookingTo, tableBooking.tableId, tableBooking.bookingId)")
    @GetGeneratedKeys
    long insertTableBooking(@Bind("tableBooking") TableBookingRecord tableBooking);
}
