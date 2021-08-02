package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingWithTableRecord;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

public interface BookingDao {

    @SqlQuery("SELECT * FROM Booking WHERE token=:token")
    @RegisterBeanMapper(BookingRecord.class)
    Optional<BookingRecord> findBookingByToken(@Bind("token") String token);

    @SqlQuery("SELECT * FROM Booking WHERE id=:id")
    @RegisterBeanMapper(BookingRecord.class)
    Optional<BookingRecord> findBookingById(@Bind("id") long id);

    @SqlUpdate("INSERT INTO " +
            "Booking(id, creation_date, booking_from_time, booking_to_time, booking_date, expiration_date, email, seats_number, status, token) " +
            "VALUES (hibernate_sequence.nextval, :creationDate, :bookingFromTime, :bookingToTime, :bookingDate, :expirationDate, :email, :seatsNumber, :status, :token)")
    @GetGeneratedKeys
    long insertBooking(@BindBean BookingRecord booking);

    @SqlUpdate("UPDATE Booking " +
            "SET creation_date=:creationDate, booking_from_time=:bookingFromTime, booking_to_time=:bookingToTime, " +
            "expiration_date=:expirationDate, email=:email, seats_number=:seatsNumber, status=:status, token=:token " +
            "WHERE id=:id")
    boolean updateBooking(@BindBean BookingRecord booking);
}
