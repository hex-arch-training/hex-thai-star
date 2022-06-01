package io.github.hexarchtraining.hts.booking.port.in.bci;


public interface FindBookingByTokenUseCase {
    BookingByTokenResult findBookingByToken(FindBookingByTokenCommand findBookingByTokenCommand);
}
