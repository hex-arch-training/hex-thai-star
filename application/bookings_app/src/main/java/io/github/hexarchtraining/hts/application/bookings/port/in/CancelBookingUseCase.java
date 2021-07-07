package io.github.hexarchtraining.hts.application.bookings.port.in;

public interface CancelBookingUseCase {
    void cancel(CancelBookingCommand command);
}
