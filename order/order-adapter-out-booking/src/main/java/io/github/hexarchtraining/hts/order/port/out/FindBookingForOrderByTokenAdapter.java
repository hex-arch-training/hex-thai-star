package io.github.hexarchtraining.hts.order.port.out;

import io.github.hexarchtraining.hts.booking.port.in.bci.BookingByTokenResult;
import io.github.hexarchtraining.hts.booking.port.in.bci.FindBookingByTokenCommand;
import io.github.hexarchtraining.hts.booking.port.in.bci.FindBookingByTokenUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindBookingForOrderByTokenAdapter implements FindBookingForOrderByTokenPort {

    private final FindBookingByTokenUseCase findBookingByTokenUseCase;

    @Override
    public FindBookingByTokenResult findBookingByToken(String token) {
        BookingByTokenResult bookingByToken = findBookingByTokenUseCase.findBookingByToken(new FindBookingByTokenCommand(token));
        return new FindBookingByTokenResult(bookingByToken.getId());
    }
}
