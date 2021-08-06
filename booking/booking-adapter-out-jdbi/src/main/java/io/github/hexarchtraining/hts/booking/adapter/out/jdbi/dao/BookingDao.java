package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import org.jdbi.v3.core.mapper.JoinRow;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterJoinRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;
import java.util.stream.Stream;

public interface BookingDao {

    @SqlQuery("SELECT * FROM Booking WHERE token=:token")
    @RegisterBeanMapper(BookingRecord.class)
    Optional<BookingRecord> findBookingByToken(@Bind("token") String token);

    @SqlQuery("SELECT * FROM Booking WHERE id=:id")
    @RegisterBeanMapper(BookingRecord.class)
    Optional<BookingRecord> findBookingById(@Bind("id") long id);

    @SqlQuery("SELECT tb.id tb_id, tb.booking_from tb_booking_from, tb.booking_to tb_booking_to, tb.table_id tb_table_id, tb.booking_id tb_booking_id, " +
            "b.id b_id, b.creation_date b_creation_date, b.booking_from_time b_booking_from_time, b.booking_to_time b_booking_to_time, " +
            "b.booking_date b_booking_date, b.expiration_date b_expiration_date, b.email b_email, b.seats_number b_seats_number, b.status b_status, b.token b_token " +
            "FROM Booking b LEFT JOIN Table_Booking tb ON b.id = tb.booking_id")
    @RegisterBeanMapper(value = BookingRecord.class, prefix = "b")
    @RegisterBeanMapper(value = TableBookingRecord.class, prefix = "tb")
    @RegisterJoinRowMapper({BookingRecord.class, TableBookingRecord.class})
    Stream<JoinRow> findAllBookingsWithTableBookingsAsStream();

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
