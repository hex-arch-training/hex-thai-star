package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface TableBookingDao {

    @SqlUpdate("DELETE FROM Table_Booking WHERE booking_id=? AND table_id=?")
    int deleteTableBooking(long bookingId, long tableId);
}
