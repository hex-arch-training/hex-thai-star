package io.github.hexarchtraining.hts.booking.common.exception;

public abstract class NotFoundException extends BusinessException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
