package io.github.hexarchtraining.hts.booking.domain.exception;

import io.github.hexarchtraining.hts.booking.common.exception.BusinessException;
import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.domain.BookingStatus;

import java.util.Optional;

public class IllegalBookingStateException extends BusinessException {

    public IllegalBookingStateException(BookingId id, BookingStatus from, BookingStatus to) {
        super(String.format("Cannot change status of booking %s: illegal transition %s -> %s.",
                Optional.ofNullable(id)
                        .map(BookingId::getValue)
                        .map(String::valueOf)
                        .orElse("(not persisted)"),
                from.name(),
                to.name()));
    }
}
