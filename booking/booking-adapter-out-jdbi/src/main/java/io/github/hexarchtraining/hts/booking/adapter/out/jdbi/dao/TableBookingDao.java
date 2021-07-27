package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface TableBookingDao {

    @SqlUpdate("DELETE FROM Table_Booking WHERE booking_id=:bookingId AND table_id=:tableId")
    void deleteTableBooking(@Bind("bookingId") long bookingId, @Bind("tableId") long tableId);
}
