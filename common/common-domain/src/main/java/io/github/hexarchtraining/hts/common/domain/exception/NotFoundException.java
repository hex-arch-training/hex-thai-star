package io.github.hexarchtraining.hts.common.domain.exception;

public abstract class NotFoundException extends BusinessException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
