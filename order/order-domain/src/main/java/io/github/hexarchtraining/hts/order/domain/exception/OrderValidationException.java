package io.github.hexarchtraining.hts.order.domain.exception;

import io.github.hexarchtraining.hts.common.domain.exception.BusinessException;

public class OrderValidationException extends BusinessException {
    public OrderValidationException(String msg) {
        super(msg);
    }
}
