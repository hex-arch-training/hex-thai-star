package io.github.hexarchtraining.hts.domain.bookings;

/**
 * A placeholder exception for any domain validity checks (to be extended later).
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}
