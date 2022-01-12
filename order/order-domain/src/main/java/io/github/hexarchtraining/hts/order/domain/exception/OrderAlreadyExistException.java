package io.github.hexarchtraining.hts.order.domain.exception;

import io.github.hexarchtraining.hts.common.domain.exception.BusinessException;

public class OrderAlreadyExistException extends BusinessException {
    public OrderAlreadyExistException(String msg) {
        super(msg);
    }
}
