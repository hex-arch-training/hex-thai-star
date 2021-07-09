package io.github.hexarchtraining.hts.bookings.service;

import io.github.hexarchtraining.hts.bookings.port.in.CancelBookingCommand;
import io.github.hexarchtraining.hts.bookings.port.in.CancelBookingUseCase;
import io.github.hexarchtraining.hts.bookings.port.out.DeleteTableBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.bookings.port.out.FindTableBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.bookings.domain.Booking;
import io.github.hexarchtraining.hts.bookings.domain.BusinessException;
import lombok.AllArgsConstructor;

import java.time.Duration;
import java.time.Instant;

@AllArgsConstructor
public class CancelBookingService implements CancelBookingUseCase {

    private final FindBookingByTokenPort findBookingByTokenPort;

    private final FindTableBookingPort findTableBookingPort;

    private final SaveBookingPort saveBookingPort;

    private final DeleteTableBookingPort deleteTableBookingPort;

    @Override
    public void cancel(CancelBookingCommand command) {

        final Booking booking = findBookingByTokenPort.find(command.getToken()).orElseThrow(() -> new BusinessException("Booking not found"));

        final Instant now = Instant.now();
        if (now.minus(Duration.ofHours(4L)).isAfter(booking.getBookingFromTime())) {
            throw new BusinessException("Too late to cancel the booking.");
        }

        booking.cancel();
        findTableBookingPort.find(booking.getId()).ifPresent(deleteTableBookingPort::delete);
        saveBookingPort.save(booking);
    }
}
