package io.github.hexarchtraining.hts.booking.usecase;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.domain.BookingStatus;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.domain.TableId;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingValidationException;
import io.github.hexarchtraining.hts.booking.port.in.CancelBookingCommand;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.booking.service.CancelBookingService;
import io.github.hexarchtraining.hts.booking.service.SendBookingStatusService;
import io.github.hexarchtraining.hts.common.adapter.out.TestTransactionAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CancelBookingServiceTest {

    private static final String VALID_EMAIL = "john.doe@acme.inc";
    private final Instant now = Instant.now();
    private final Instant validFromTime = now.plus(25, ChronoUnit.HOURS);
    private final Instant validToTime = now.plus(27, ChronoUnit.HOURS);
    private final String token = "CB_467365462647836274673";

    @InjectMocks
    private CancelBookingService cancelBookingService;

    @Mock
    private FindBookingByTokenPort findBookingByTokenPort;

    @Mock
    private SaveBookingPort saveBookingPort;

    @Mock
    private SendBookingStatusService sendBookingStatusService;

    @Spy
    private TestTransactionAdapter testTransactionAdapter = new TestTransactionAdapter();

    @Test
    public void shouldThrowExceptionIfBookingNotFound() {
        // given
        when(findBookingByTokenPort.find(token)).thenReturn(Optional.empty());
        final CancelBookingCommand command = new CancelBookingCommand(token);

        // when, then
        assertThrows(BookingNotFoundException.class, () -> cancelBookingService.cancel(command));
    }

    @Test
    public void shouldThrowExceptionIfCancellationIsTooLate() {
        // given
        final Instant now = Instant.now();
        final Booking booking = Booking.builder()
            .bookingFromTime(now.plus(Duration.ofHours(3)))
            .status(BookingStatus.NEW)
            .tableBookings(new HashSet<>())
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
        booking.addTableBooking(table);

        when(findBookingByTokenPort.find(token)).thenReturn(Optional.of(booking));
        final CancelBookingCommand command = new CancelBookingCommand(token);

        // when
        cancelBookingService.cancel(command);

        // then
        assertEquals(BookingStatus.CANCELLED, booking.getStatus());
        verify(testTransactionAdapter).inTransaction(any());
        verify(saveBookingPort).save(booking);
        verify(sendBookingStatusService).sendBookingStatus(booking);
    }
}
