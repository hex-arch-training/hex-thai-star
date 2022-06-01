package io.github.hexarchtraining.hts.booking.port.in;


public interface FindBookingByTokenUseCase {
    BookingByTokenResult findBookingByToken(FindBookingByTokenCommand findBookingByTokenCommand);
}
