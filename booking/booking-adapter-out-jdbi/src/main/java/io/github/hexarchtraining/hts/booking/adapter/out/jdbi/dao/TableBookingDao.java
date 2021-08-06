package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.Instant;
import java.util.List;

public interface TableBookingDao {

    @SqlUpdate("DELETE FROM Table_Booking WHERE booking_id=:bookingId AND table_id=:tableId")
    void deleteTableBooking(@Bind("bookingId") long bookingId, @Bind("tableId") long tableId);

    @SqlQuery("SELECT * FROM Table_Booking WHERE booking_id=:bookingId")
    @RegisterBeanMapper(TableBookingRecord.class)
    List<TableBookingRecord> findAllTableBookingsForBooking(@Bind("bookingId") long bookingId);

    @SqlQuery("SELECT * FROM Table_Booking " +
            "WHERE (booking_from <= :fromTime AND booking_to >= :fromTime) " +
            "OR (booking_from <= :toTime AND booking_to >= :toTime) " +
            "OR (booking_from > :fromTime AND booking_to < :toTime)")
    @RegisterBeanMapper(TableBookingRecord.class)
    List<TableBookingRecord> findBookingsIntersect(@Bind("fromTime") Instant from, @Bind("toTime") Instant to);

    @SqlUpdate("INSERT INTO Table_Booking (id, booking_from, booking_to, table_id, booking_id, seats_number) " +
            "VALUES (hibernate_sequence.nextval, :bookingFrom, :bookingTo, :tableId, :bookingId, :seatsNumber)")
    @GetGeneratedKeys
    long insertTableBooking(@BindBean TableBookingRecord tableBooking);

    @SqlUpdate("UPDATE Table_Booking " +
            "SET booking_from=:bookingFrom, booking_to=:bookingTo, seats_number=:seatsNumber " +
            "WHERE id=:id")
    boolean updateTableBooking(@BindBean TableBookingRecord tableBooking);
}
