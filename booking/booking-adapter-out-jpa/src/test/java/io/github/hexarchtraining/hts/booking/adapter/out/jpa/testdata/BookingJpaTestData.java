package io.github.hexarchtraining.hts.booking.adapter.out.jpa.testdata;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.BookingEntity;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableBookingEntity;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableEntity;
import io.github.hexarchtraining.hts.booking.domain.BookingStatus;
import org.assertj.core.util.Sets;

import java.time.Instant;

public class BookingJpaTestData {

    // entity Table
    public final static int TABLE__MAX_SEATS = 4;

    // entity TableBooking
    public final static Instant TABLE_BOOKING__BOOKING_FROM = Instant.parse("2021-08-28T17:00:00Z");
    public final static Instant TABLE_BOOKING__BOOKING_TO = Instant.parse("2021-08-28T21:00:00Z");
    public final static int TABLE_BOOKING__SEATS_NUMBER = 2;

    // entity Booking
    public final static Instant BOOKING__CREATION_DATE = Instant.parse("2021-06-28T17:00:00Z");
    public final static Instant BOOKING__BOOKING_FROM_TIME = Instant.parse("2021-08-28T17:30:00Z");
    public final static Instant BOOKING__BOOKING_TO_TIME = Instant.parse("2021-08-28T17:30:00Z");
    public final static Instant BOOKING__BOOKING_DATE = Instant.parse("2021-08-28T13:00:00Z");
    public final static Instant BOOKING__EXPIRATION_DATE = Instant.parse("2021-07-28T17:00:00Z");
    public final static String BOOKING__EMAIL = "john@example.com";
    public final static int BOOKING__SEATS_NUMBER = 3;
    public final static BookingStatus BOOKING__STATUS = BookingStatus.CONFIRMED;
    public final static String BOOKING__TOKEN = "CB_20210827_18e151ee9dc8c89d3f3c4e705f6b79ee";

    public static TableEntity newTable() {
        TableEntity table = new TableEntity();
        table.setMaxSeats(TABLE__MAX_SEATS);
        return table;
    }

    public static TableBookingEntity newTableBooking() {
        TableBookingEntity tableBooking = new TableBookingEntity();
        tableBooking.setBookingTo(TABLE_BOOKING__BOOKING_TO);
        tableBooking.setBookingFrom(TABLE_BOOKING__BOOKING_FROM);
        tableBooking.setSeatsNumber(TABLE_BOOKING__SEATS_NUMBER);
        tableBooking.setTable(newTable());

        return tableBooking;
    }

    public static BookingEntity newBooking() {
        BookingEntity booking = new BookingEntity();
        booking.setCreationDate(BOOKING__CREATION_DATE);
        booking.setBookingFromTime(BOOKING__BOOKING_FROM_TIME);
        booking.setBookingToTime(BOOKING__BOOKING_TO_TIME);
        booking.setBookingDate(BOOKING__BOOKING_DATE);
        booking.setExpirationDate(BOOKING__EXPIRATION_DATE);
        booking.setEmail(BOOKING__EMAIL);
        booking.setSeatsNumber(BOOKING__SEATS_NUMBER);
        booking.setStatus(BOOKING__STATUS);
        booking.setToken(BOOKING__TOKEN);
        booking.setTableBookings(Sets.set(newTableBooking()));

        return booking;
    }

}
