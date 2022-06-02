package io.github.hexarchtraining.hts.order.adapter.out.booking;

import io.github.hexarchtraining.hts.booking.port.in.BookingByTokenResult;
import io.github.hexarchtraining.hts.booking.port.in.FindBookingByTokenCommand;
import io.github.hexarchtraining.hts.booking.port.in.FindBookingByTokenUseCase;
import io.github.hexarchtraining.hts.order.port.out.FindBookingByTokenResult;
import io.github.hexarchtraining.hts.order.port.out.FindBookingForOrderByTokenPort;
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
