package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;

public interface BookingDao {

    @SqlQuery("SELECT * FROM Booking WHERE token=:token")
    @RegisterBeanMapper(BookingRecord.class)
    Optional<BookingRecord> findBookingByToken(@Bind("token") String token);

    @SqlQuery("SELECT * FROM Booking WHERE id=:id")
    @RegisterBeanMapper(BookingRecord.class)
    Optional<BookingRecord> findBookingById(@Bind("id") long id);

    @SqlUpdate("INSERT INTO " +
            "Booking(creation_date, booking_from_time, booking_to_time, expiration_date, email, seats_number, status, token) " +
            "VALUES (booking.creationDate, booking.bookingFromTime, booking.bookingToTime, booking.expirationDate, booking.email, booking.seatsNumber, booking.status, booking.token)")
    @GetGeneratedKeys
    long insertBooking(@Bind("booking") BookingRecord booking);

    @SqlUpdate("UPDATE Booking " +
            "SET creation_date=:booking.creationDate, booking_from_time=:booking.bookingFromTime, booking_to_time=:booking.bookingToTime, " +
            "expiration_date=:booking.expirationDate, email=:booking.email, seats_number=:booking.seatsNumber, status=:booking.status, token=:booking.token " +
            "WHERE id=:booking.id")
    boolean updateBooking(@Bind("booking") BookingRecord booking);
}
