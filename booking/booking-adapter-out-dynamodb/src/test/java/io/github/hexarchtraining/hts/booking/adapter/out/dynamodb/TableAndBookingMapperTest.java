package io.github.hexarchtraining.hts.booking.adapter.out.dynamodb;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.github.hexarchtraining.hts.booking.adapter.out.dynamodb.TableAndBookingMapper.*;

class TableAndBookingMapperTest {
    private final String bookingToken = "CB_20210827_18e151ee9dc8c89d3f3c4e705f6b79ee";
    private final long bookingId = 6394287366L;
    private final String email = "john@example.com";
    private final int seatsNumber = 2;
    private final String status = "NEW";
    private final String bookingDateInIso = "2021-08-28T00:00:00Z";
    private final String bookingFromTimeInIso = "2021-08-28T17:00:00Z";
    private final String bookingToTimeInIso = "2021-08-28T21:00:00Z";
    private final String creationDateInIso = "2021-08-27T09:58:37.204794Z";
    private final String expirationDateInIso = "2021-08-27T00:00:00Z";

    private final long tableId = 3823163367L;
    private final int maxSeats = 2;

    @Test
    void mapsToTable() {
        // given
        Map<String, AttributeValue> tableAttrs = new HashMap<>();
        final AttributeValue tableKey = AttributeValue.builder().s(TABLE_ID_PREFIX.concat(String.valueOf(tableId))).build();
        tableAttrs.put(PK_ATTR_NAME, tableKey);
        tableAttrs.put(SK_ATTR_NAME, tableKey);
        tableAttrs.put(TABLE_MAX_SEATS_ATTR_NAME, AttributeValue.builder().n(String.valueOf(maxSeats)).build());
        // when
        final List<Table> tables = new TableAndBookingMapper().parse(Collections.singletonList(tableAttrs)).getTables();
        // then
        assertEquals(1, tables.size());
        final Table table = tables.get(0);
        assertEquals(tableId, table.getId().getValue());
        assertEquals(maxSeats, table.getMaxSeats());
    }

    @Test
    void mapsToBooking() {
        // given
        final Map<String, AttributeValue> bookingAttrs = createBookingAttributes();
        // when
        final List<Booking> bookings = new TableAndBookingMapper().parse(Collections.singletonList(bookingAttrs)).getBookings();
        // then
        assertEquals(1, bookings.size());
        assertBookingEquals(bookings.get(0));
    }

    @Test
    void mapsToBookingWithTableBookings() {
        // given
        final Map<String, AttributeValue> bookingAttrs = createBookingAttributes();
        final Map<String, AttributeValue> tableBookingAttrs = new HashMap<>();
        final AttributeValue bookingKey = AttributeValue.builder().s(BOOKING_TOKEN_PREFIX.concat(bookingToken)).build();
        final AttributeValue tableKey = AttributeValue.builder().s(TABLE_ID_PREFIX.concat(String.valueOf(tableId))).build();
        tableBookingAttrs.put(PK_ATTR_NAME, bookingKey);
        tableBookingAttrs.put(SK_ATTR_NAME, tableKey);
        tableBookingAttrs.put(BOOKING_SEATS_NUMBER_ATTR_NAME, AttributeValue.builder().n(String.valueOf(maxSeats)).build());
        // when
        final List<Booking> bookings = new TableAndBookingMapper().parse(
                Arrays.asList(bookingAttrs, tableBookingAttrs)).getBookings();
        // then
        assertEquals(1, bookings.size());
        final Booking booking = bookings.get(0);
        assertBookingEquals(booking);
        assertEquals(1, booking.getTableBookings().size());
        final TableBooking tableBooking = booking.getTableBookings().iterator().next();
        assertEquals(tableId, tableBooking.getTableId().getValue());
        assertEquals(maxSeats, tableBooking.getSeatsNumber());
    }

    private void assertBookingEquals(Booking booking) {
        assertEquals(bookingToken, booking.getToken());
        assertEquals(bookingId, booking.getId().getValue());
        assertEquals(email, booking.getEmail());
        assertEquals(status, booking.getStatus().toString());
        assertEquals(seatsNumber, booking.getSeatsNumber());
        assertEquals(bookingDateInIso, booking.getBookingDate().toString());
        assertEquals(bookingFromTimeInIso, booking.getBookingFromTime().toString());
        assertEquals(bookingToTimeInIso, booking.getBookingToTime().toString());
        assertEquals(creationDateInIso, booking.getCreationDate().toString());
        assertEquals(expirationDateInIso, booking.getExpirationDate().toString());
    }

    private Map<String, AttributeValue> createBookingAttributes() {
        Map<String, AttributeValue> bookingAttrs = new HashMap<>();
        final AttributeValue bookingKey = AttributeValue.builder().s(BOOKING_TOKEN_PREFIX.concat(bookingToken)).build();
        bookingAttrs.put(PK_ATTR_NAME, bookingKey);
        bookingAttrs.put(SK_ATTR_NAME, bookingKey);
        bookingAttrs.put(BOOKING_ID_ATTR_NAME, AttributeValue.builder().n(String.valueOf(bookingId)).build());
        bookingAttrs.put(BOOKING_CREATION_DATE_ATTR_NAME, AttributeValue.builder().s(creationDateInIso).build());
        bookingAttrs.put(BOOKING_FROM_TIME_ATTR_NAME, AttributeValue.builder().s(bookingFromTimeInIso).build());
        bookingAttrs.put(BOOKING_TO_TIME_ATTR_NAME, AttributeValue.builder().s(bookingToTimeInIso).build());
        bookingAttrs.put(BOOKING_DATE_ATTR_NAME, AttributeValue.builder().s(bookingDateInIso).build());
        bookingAttrs.put(BOOKING_EXPIRATION_DATE_ATTR_NAME, AttributeValue.builder().s(expirationDateInIso).build());
        bookingAttrs.put(BOOKING_EMAIL_ATTR_NAME, AttributeValue.builder().s(email).build());
        bookingAttrs.put(BOOKING_SEATS_NUMBER_ATTR_NAME, AttributeValue.builder().n(String.valueOf(seatsNumber)).build());
        bookingAttrs.put(BOOKING_STATUS_ATTR_NAME, AttributeValue.builder().s(status).build());

        return bookingAttrs;
    }
}
