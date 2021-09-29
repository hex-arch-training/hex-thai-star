package io.github.hexarchtraining.hts.common.domain.token.exception;

import io.github.hexarchtraining.hts.common.domain.exception.BusinessException;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException(String msg) {
        super(msg);
    }
}
