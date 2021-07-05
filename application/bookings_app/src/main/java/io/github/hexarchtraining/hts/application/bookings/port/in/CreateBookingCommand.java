package io.github.hexarchtraining.hts.application.bookings.port.in;

import io.github.hexarchtraining.hts.domain.bookings.BusinessException;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;
import java.util.regex.Pattern;

@Value
public class CreateBookingCommand {

    Instant bookingFrom;

    Instant bookingTo;

    String email;

    int seatsNumber;

    public CreateBookingCommand(@NonNull Instant bookingFrom, @NonNull Instant bookingTo, @NonNull String email, int seatsNumber) {
        this.bookingFrom = bookingFrom;
        this.bookingTo = bookingTo;
        this.email = email;
        this.seatsNumber = seatsNumber;
        if (bookingFrom.isAfter(bookingTo)) {
            throw new BusinessException("Invalid booking window");
        }
        checkEmail(this.email);
        checkSeatsNumber(this.seatsNumber);
    }

    private static void checkEmail(String email) {
        final String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        final Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(email).matches()) {
            throw new BusinessException("Invalid email provided.");
        }
    }

    private static void checkSeatsNumber(int seatsNumber) {
        if (seatsNumber < 1) {
            throw new BusinessException(String.format("Illegal number of seats in the booking: %d.", seatsNumber));
        }
    }
}