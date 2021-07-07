package io.github.hexarchtraining.hts.application.bookings.port.in;

public interface ConfirmBookingUseCase {

    void confirm(ConfirmBookingCommand command);
}
