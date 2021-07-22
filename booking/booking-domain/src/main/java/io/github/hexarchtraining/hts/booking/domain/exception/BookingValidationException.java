package io.github.hexarchtraining.hts.booking.domain.exception;

import io.github.hexarchtraining.hts.booking.common.exception.BusinessException;

public class BookingValidationException extends BusinessException {
    public BookingValidationException(String msg) {
        super(msg);
    }
}
