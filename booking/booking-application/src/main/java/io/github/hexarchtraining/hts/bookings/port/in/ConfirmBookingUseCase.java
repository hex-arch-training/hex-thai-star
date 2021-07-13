package io.github.hexarchtraining.hts.bookings.port.in;

public interface ConfirmBookingUseCase {

    void confirm(ConfirmBookingCommand command);
}
