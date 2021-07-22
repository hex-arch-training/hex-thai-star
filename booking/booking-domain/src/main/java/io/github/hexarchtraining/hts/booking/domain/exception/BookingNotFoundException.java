package io.github.hexarchtraining.hts.booking.domain.exception;

import io.github.hexarchtraining.hts.booking.common.exception.NotFoundException;
import io.github.hexarchtraining.hts.booking.domain.BookingId;

import java.util.Optional;

public class BookingNotFoundException extends NotFoundException {

    public BookingNotFoundException(String token) {
        super(String.format("Booking for token %s cannot be found.", token));
    }

    public BookingNotFoundException(BookingId id) {
        super(String.format("Booking %s cannot be found.", Optional.ofNullable(id)
                .map(BookingId::getValue)
                .map(String::valueOf)
                .orElse("(not persisted)")));
    }
}
