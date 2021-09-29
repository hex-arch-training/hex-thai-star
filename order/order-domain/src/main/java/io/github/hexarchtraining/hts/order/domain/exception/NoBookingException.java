package io.github.hexarchtraining.hts.order.domain.exception;

import io.github.hexarchtraining.hts.common.domain.exception.BusinessException;

public class NoBookingException extends BusinessException {
    public NoBookingException(String msg) {
        super(msg);
    }
}
