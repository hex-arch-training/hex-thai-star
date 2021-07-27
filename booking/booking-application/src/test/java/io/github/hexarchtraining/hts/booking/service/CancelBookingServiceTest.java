package io.github.hexarchtraining.hts.booking.service;

import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.port.in.CancelBookingCommand;
import io.github.hexarchtraining.hts.booking.port.in.CancelBookingUseCase;
import io.github.hexarchtraining.hts.booking.port.out.DeleteTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.common.adapter.out.TestTransactionAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CancelBookingServiceTest {

    private static final String VALID_EMAIL = "john.doe@acme.inc";

    private final Instant now = Instant.now();

    private final Instant validFromTime = now.plus(25, ChronoUnit.HOURS);

    private final Instant validToTime = now.plus(27, ChronoUnit.HOURS);

    private final String token = "CB_467365462647836274673";

    private FindBookingByTokenPort findBookingByTokenPort;

    private FindTableBookingPort findTableBookingPort;

    private TestTransactionAdapter testTransactionAdapter;

    private CancelBookingUseCase cancelBookingUseCase;

    private SaveBookingPort saveBookingPort;

    private DeleteTableBookingPort deleteTableBookingPort;

    @BeforeEach
    public void setUpMocks() {
        testTransactionAdapter = new TestTransactionAdapter();
        findBookingByTokenPort = mock(FindBookingByTokenPort.class);
        findTableBookingPort = mock(FindTableBookingPort.class);
        saveBookingPort = mock(SaveBookingPort.class);
        deleteTableBookingPort = mock(DeleteTableBookingPort.class);
        cancelBookingUseCase = new CancelBookingService(findBookingByTokenPort, findTableBookingPort, saveBookingPort, deleteTableBookingPort, testTransactionAdapter);
    }

    @Test
    public void shouldThrowExceptionIfBookingNotFound() {
        when(findBookingByTokenPort.find(token)).thenReturn(Optional.empty());
        final CancelBookingCommand command = new CancelBookingCommand(token);
        assertThrows(BookingNotFoundException.class, () -> {
            cancelBookingUseCase.cancel(command);
        });
    }

}
