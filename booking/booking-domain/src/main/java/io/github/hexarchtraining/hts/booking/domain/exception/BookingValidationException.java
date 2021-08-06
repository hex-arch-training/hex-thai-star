package io.github.hexarchtraining.hts.booking.domain.exception;

import io.github.hexarchtraining.hts.common.domain.exception.BusinessException;

public class BookingValidationException extends BusinessException {
    public BookingValidationException(String msg) {
        super(msg);
    }
}
