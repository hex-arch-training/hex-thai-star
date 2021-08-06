package io.github.hexarchtraining.hts.booking.domain.exception;

import io.github.hexarchtraining.hts.common.domain.exception.BusinessException;
import io.github.hexarchtraining.hts.booking.domain.BookingId;

public class IncompleteBookingException extends BusinessException {
    public IncompleteBookingException(BookingId id, String reason) {
        super(String.format("The booking %d is incomplete due to %s.", id.getValue(), reason));
    }
}
