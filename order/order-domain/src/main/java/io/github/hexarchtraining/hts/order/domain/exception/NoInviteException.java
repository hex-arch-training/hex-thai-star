package io.github.hexarchtraining.hts.order.domain.exception;

import io.github.hexarchtraining.hts.common.domain.exception.BusinessException;

public class NoInviteException extends BusinessException {
    public NoInviteException(String msg) {
        super(msg);
    }
}
