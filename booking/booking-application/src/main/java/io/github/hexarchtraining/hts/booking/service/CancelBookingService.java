package io.github.hexarchtraining.hts.booking.service;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingValidationException;
import io.github.hexarchtraining.hts.booking.port.in.CancelBookingCommand;
import io.github.hexarchtraining.hts.booking.port.in.CancelBookingUseCase;
import io.github.hexarchtraining.hts.booking.port.out.DeleteTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import lombok.AllArgsConstructor;

import java.time.Duration;
import java.time.Instant;

@AllArgsConstructor
public class CancelBookingService implements CancelBookingUseCase {

    private final FindBookingByTokenPort findBookingByTokenPort;

    private final FindTableBookingPort findTableBookingPort;

    private final SaveBookingPort saveBookingPort;

    private final DeleteTableBookingPort deleteTableBookingPort;

    private final TransactionPort transactionPort;

    @Override
    public void cancel(CancelBookingCommand command) {
        transactionPort.withTransaction(() -> {
            final Booking booking = findBookingByTokenPort.find(command.getToken()).orElseThrow(() -> new BookingNotFoundException(command.getToken()));

            final Instant now = Instant.now();
            if (now.minus(Duration.ofHours(4L)).isAfter(booking.getBookingFromTime())) {
                throw new BookingValidationException("Too late to cancel the booking.");
            }

            booking.cancel();
            findTableBookingPort.find(booking.getId()).ifPresent(deleteTableBookingPort::delete);
            saveBookingPort.save(booking);
        });
    }
}
