package io.github.hexarchtraining.hts.booking.port.in;

public interface CancelBookingUseCase {
    void cancel(CancelBookingCommand command);
}
