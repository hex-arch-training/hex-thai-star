package io.github.hexarchtraining.hts.booking.domain;

/**
 * A placeholder exception for any domain validity checks (to be extended later).
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
