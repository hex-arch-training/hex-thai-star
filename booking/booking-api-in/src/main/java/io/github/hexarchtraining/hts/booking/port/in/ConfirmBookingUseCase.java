package io.github.hexarchtraining.hts.booking.port.in;

public interface ConfirmBookingUseCase {

    void confirm(ConfirmBookingCommand command);
}
