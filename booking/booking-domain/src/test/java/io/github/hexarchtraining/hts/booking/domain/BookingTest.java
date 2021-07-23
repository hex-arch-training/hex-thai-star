package io.github.hexarchtraining.hts.booking.domain;


import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    private static final String VALID_EMAIL = "john.doe@acme.inc";

    private final Instant now = Instant.now();

    @Test
    void shouldAllowToCreateBookingInProperAdvance() {
        // given
        final Instant bookingFromTime = now.plus(25, ChronoUnit.HOURS);
        final Instant bookingToTime = now.plus(27, ChronoUnit.HOURS);
        // when
        final Booking booking = Booking.createNewBooking(
                bookingFromTime,
                bookingToTime,
                VALID_EMAIL,
                2);
        // then
        assertNotNull(booking);
        assertEquals(VALID_EMAIL, booking.getEmail());
        assertEquals(bookingFromTime, booking.getBookingFromTime());
        assertEquals(bookingToTime, booking.getBookingToTime());
        assertEquals(bookingFromTime.truncatedTo(ChronoUnit.DAYS), booking.getBookingDate());
        assertEquals(2, booking.getSeatsNumber());
        assertEquals(BookingStatus.NEW, booking.getStatus());
        assertNotNull(booking.getToken());
        assertTrue(booking.getToken().startsWith("CB_"));
    }

    @Test
    void shouldFailIfCreatingBookingWithInvalidTimeWindow() {
        assertThrows(BusinessException.class, () -> Booking.createNewBooking(
                now.plus(26, ChronoUnit.HOURS),
                now.plus(25, ChronoUnit.HOURS),
                VALID_EMAIL,
                2));
    }

    @Test
    void shouldFailIfTryingToBookInThePast() {
        assertThrows(BusinessException.class, () -> Booking.createNewBooking(
                now.minus(24, ChronoUnit.HOURS),
                now.minus(20, ChronoUnit.HOURS),
                VALID_EMAIL,
                2));
    }
}
