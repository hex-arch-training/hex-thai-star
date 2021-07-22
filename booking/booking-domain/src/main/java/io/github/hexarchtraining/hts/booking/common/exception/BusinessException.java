package io.github.hexarchtraining.hts.booking.common.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable e) {
        super(e);
    }
}
