package io.github.hexarchtraining.hts.booking.service;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.port.in.BookingByTokenResult;
import io.github.hexarchtraining.hts.booking.port.in.FindBookingByTokenCommand;
import io.github.hexarchtraining.hts.booking.port.in.FindBookingByTokenUseCase;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindBookingByTokenService implements FindBookingByTokenUseCase {

    private final FindBookingByTokenPort findBookingByTokenPort;

    @Override
    public BookingByTokenResult findBookingByToken(FindBookingByTokenCommand findBookingByTokenCommand) {
        Booking booking = findBookingByTokenPort.find(findBookingByTokenCommand.getToken()).orElseThrow(() -> new BookingNotFoundException(findBookingByTokenCommand.getToken()));
        return new BookingByTokenResult(booking.getId().getValue());
    }
}
