package io.github.hexarchtraining.hts.bookings.service;

import io.github.hexarchtraining.hts.bookings.port.in.ConfirmBookingCommand;
import io.github.hexarchtraining.hts.bookings.port.in.ConfirmBookingUseCase;
import io.github.hexarchtraining.hts.bookings.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.bookings.port.out.FindTableBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.bookings.domain.Booking;
import io.github.hexarchtraining.hts.bookings.domain.BusinessException;
import io.github.hexarchtraining.hts.bookings.domain.TableBooking;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmBookingService implements ConfirmBookingUseCase {

    private final FindBookingByTokenPort findBookingByTokenPort;

    private final FindTableBookingPort findTableBookingPort;

    private final SaveBookingPort saveBookingPort;

    @Override
    public void confirm(ConfirmBookingCommand command) {

        final Booking booking = findBookingByTokenPort.find(command.getToken()).orElseThrow(() -> new BusinessException("Cannot find booking for given token."));
        final TableBooking tableBooking = findTableBookingPort.find(booking.getId()).orElseThrow(() -> new BusinessException("Booking is incomplete and cannot be confirmed."));

        booking.confirm();
        saveBookingPort.save(booking);
    }
}
