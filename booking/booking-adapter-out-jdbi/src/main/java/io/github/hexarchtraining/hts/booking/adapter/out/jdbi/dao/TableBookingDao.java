package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;

public interface TableBookingDao {

    @SqlUpdate("DELETE FROM Table_Booking WHERE booking_id=:bookingId AND table_id=:tableId")
    void deleteTableBooking(@Bind("bookingId") long bookingId, @Bind("tableId") long tableId);

    @SqlQuery("SELECT * FROM Table_Booking WHERE booking_id=:bookingId")
    Optional<TableBookingRecord> findTableBooking(@Bind("bookingId") long bookingId);

    @SqlUpdate("INSERT INTO Table_Booking (booking_from, booking_to, table_id, booking_id) " +
            "VALUES (tableBooking.bookingFrom, tableBooking.bookingTo, tableBooking.tableId, tableBooking.bookingId)")
    @GetGeneratedKeys
    long insertTableBooking(@Bind("tableBooking") TableBookingRecord tableBooking);
}
