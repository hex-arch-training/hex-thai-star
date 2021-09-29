package io.github.hexarchtraining.hts.booking.service.bci;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.port.in.bci.BookingByTokenResult;
import io.github.hexarchtraining.hts.booking.port.in.bci.FindBookingByTokenCommand;
import io.github.hexarchtraining.hts.booking.port.in.bci.FindBookingByTokenUseCase;
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
