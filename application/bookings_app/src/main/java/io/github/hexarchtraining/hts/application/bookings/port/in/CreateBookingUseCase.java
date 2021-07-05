package io.github.hexarchtraining.hts.application.bookings.port.in;


public interface CreateBookingUseCase {

    void createBooking(CreateBookingCommand command);
}
