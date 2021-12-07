package io.github.hexarchtraining.hts.booking.adapter.in.springweb;

import io.github.hexarchtraining.hts.booking.domain.exception.BookingValidationException;
import io.github.hexarchtraining.hts.booking.domain.exception.IllegalBookingStateException;
import io.github.hexarchtraining.hts.booking.domain.exception.IncompleteBookingException;
import io.github.hexarchtraining.hts.common.domain.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<String> handle(NotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(BookingValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(IncompleteBookingException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(IllegalBookingStateException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
