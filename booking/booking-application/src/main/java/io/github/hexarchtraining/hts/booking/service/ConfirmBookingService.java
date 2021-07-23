package io.github.hexarchtraining.hts.booking.service;

import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.domain.exception.IncompleteBookingException;
import io.github.hexarchtraining.hts.booking.port.in.ConfirmBookingCommand;
import io.github.hexarchtraining.hts.booking.port.in.ConfirmBookingUseCase;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmBookingService implements ConfirmBookingUseCase {

    private final FindBookingByTokenPort findBookingByTokenPort;

    private final FindTableBookingPort findTableBookingPort;

    private final SaveBookingPort saveBookingPort;

    @Override
    public void confirm(ConfirmBookingCommand command) {

        final Booking booking = findBookingByTokenPort.find(command.getToken()).orElseThrow(() -> new BookingNotFoundException(command.getToken()));
        findTableBookingPort.find(booking.getId()).orElseThrow(() -> new IncompleteBookingException(booking.getId(), "lack of table booking"));

        booking.confirm();
        saveBookingPort.save(booking);
    }
}
