package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.Optional;

public interface BookingDao {

    @SqlQuery("SELECT * FROM Booking WHERE token = :token")
    Optional<BookingRecord> findBookingByToken(@Bind("token") String token);
}
