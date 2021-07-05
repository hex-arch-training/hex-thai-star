package io.github.hexarchtraining.hts.application.bookings.service;

import io.github.hexarchtraining.hts.application.bookings.port.in.CreateBookingCommand;
import io.github.hexarchtraining.hts.application.bookings.port.in.CreateBookingUseCase;
import io.github.hexarchtraining.hts.application.bookings.port.out.CreateBookingPort;
import io.github.hexarchtraining.hts.domain.bookings.Booking;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateBookingService implements CreateBookingUseCase {

    private final CreateBookingPort createBookingPort;

    @Override
    public void createBooking(CreateBookingCommand command) {
        final Booking booking = Booking.createNewBooking(command.getBookingTime(), command.getEmail(), command.getSeatsNumber());
        createBookingPort.createBooking(booking);
    }
}
