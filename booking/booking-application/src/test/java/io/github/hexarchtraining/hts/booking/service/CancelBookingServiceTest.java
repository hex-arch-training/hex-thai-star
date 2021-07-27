package io.github.hexarchtraining.hts.booking.service;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.domain.BookingStatus;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.domain.TableId;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingValidationException;
import io.github.hexarchtraining.hts.booking.port.in.CancelBookingCommand;
import io.github.hexarchtraining.hts.booking.port.out.DeleteTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.common.adapter.out.TestTransactionAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class CancelBookingServiceTest {

    private static final String VALID_EMAIL = "john.doe@acme.inc";

    private final Instant now = Instant.now();

    private final Instant validFromTime = now.plus(25, ChronoUnit.HOURS);

    private final Instant validToTime = now.plus(27, ChronoUnit.HOURS);

    private final String token = "CB_467365462647836274673";

    private FindBookingByTokenPort findBookingByTokenPort;

    private FindTableBookingPort findTableBookingPort;

    private TestTransactionAdapter testTransactionAdapter;

    private CancelBookingService cancelBookingService;

    private SaveBookingPort saveBookingPort;

    private DeleteTableBookingPort deleteTableBookingPort;

    @BeforeEach
    public void setUpMocks() {
        testTransactionAdapter = new TestTransactionAdapter();
        findBookingByTokenPort = mock(FindBookingByTokenPort.class);
        findTableBookingPort = mock(FindTableBookingPort.class);
        saveBookingPort = mock(SaveBookingPort.class);
        deleteTableBookingPort = mock(DeleteTableBookingPort.class);
        cancelBookingService = new CancelBookingService(findBookingByTokenPort, findTableBookingPort, saveBookingPort, deleteTableBookingPort, testTransactionAdapter);
    }

    @Test
    public void shouldThrowExceptionIfBookingNotFound() {
        // given
        when(findBookingByTokenPort.find(token)).thenReturn(Optional.empty());
        final CancelBookingCommand command = new CancelBookingCommand(token);
        // then
        assertThrows(BookingNotFoundException.class, () -> cancelBookingService.cancel(command));
    }

    @Test
    public void shouldThrowExceptionIfCancellationIsTooLate() {
        // given
        final Instant now = Instant.now();
        final Booking booking = Booking.builder()
                .bookingFromTime(now.plus(Duration.ofHours(3)))
                .status(BookingStatus.NEW)
                .build();
        when(findBookingByTokenPort.find(token)).thenReturn(Optional.of(booking));
        final CancelBookingCommand command = new CancelBookingCommand(token);
        // then
        assertThrows(BookingValidationException.class, () -> cancelBookingService.cancel(command));
    }

    @Test
    public void shouldCancelBookingAndReleaseTable() {
        // given
        final BookingId id = new BookingId(678L);
        final Booking booking = Booking.createNewBooking(
                id,
                validFromTime,
                validToTime,
                VALID_EMAIL,
                5);
        final Table table = Table.builder()
                .id(new TableId(456))
                .maxSeats(10)
                .build();
        final TableBooking tableBooking = TableBooking.createTableBooking(
                booking,
                table,
                booking.getBookingFromTime(),
                booking.getBookingToTime());

        when(findBookingByTokenPort.find(token)).thenReturn(Optional.of(booking));
        when(findTableBookingPort.find(id)).thenReturn(Optional.of(tableBooking));
        final CancelBookingCommand command = new CancelBookingCommand(token);
        // when
        cancelBookingService.cancel(command);
        // then
        assertEquals(BookingStatus.CANCELLED, booking.getStatus());
        assertTrue(testTransactionAdapter.hasBeenCalled());
        verify(deleteTableBookingPort).delete(tableBooking);
        verify(saveBookingPort).save(booking);
    }
}
