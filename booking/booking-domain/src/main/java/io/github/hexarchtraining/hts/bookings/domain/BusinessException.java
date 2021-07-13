package io.github.hexarchtraining.hts.bookings.domain;

/**
 * A placeholder exception for any domain validity checks (to be extended later).
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
