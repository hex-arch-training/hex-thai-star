package io.github.hexarchtraining.hts.bookings.port.in;

public interface CancelBookingUseCase {
    void cancel(CancelBookingCommand command);
}
