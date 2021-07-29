package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
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

    @SqlQuery("SELECT tb.id tb_id, tb.booking_from tb_booking_from, tb.booking_to tb_booking_to, tb.table_id tb_table_id, tb.booking_id tb_booking_id, " +
            "b.id b_id, b.creation_date b_creation_date, b.booking_from_time b_booking_from_time, b.booking_to_time b_booking_to_time, " +
            "b.booking_date b_booking_date, b.expiration_date b_expiration_date, b.email b_email, b.seats_number b_seats_number, b.status b_status, b.token b_token " +
            "FROM Table_Booking tb JOIN Booking b ON tb.booking_id = b.id")
    @RegisterBeanMapper(value = TableBookingRecord.class, prefix = "tb")
    @RegisterBeanMapper(value = BookingRecord.class, prefix = "b")
    Map<TableBookingRecord, BookingRecord> findAllTableBookingsWithBooking();

    @SqlQuery("SELECT * FROM Table_Booking " +
            "WHERE (booking_from <= :fromTime AND booking_to >= :fromTime) " +
            "OR (booking_from <= :toTime AND booking_to >= :toTime) " +
            "OR (booking_from > :fromTime AND booking_to < :toTime)")
    @RegisterBeanMapper(TableBookingRecord.class)
    List<TableBookingRecord> findBookingsIntersect(@Bind("fromTime") Instant from, @Bind("toTime") Instant to);

    @SqlUpdate("INSERT INTO Table_Booking (id, booking_from, booking_to, table_id, booking_id) " +
            "VALUES (hibernate_sequence.nextval, :bookingFrom, :bookingTo, :tableId, :bookingId)")
    @GetGeneratedKeys
    long insertTableBooking(@BindBean TableBookingRecord tableBooking);
}
