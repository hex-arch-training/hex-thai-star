package io.github.hexarchtraining.hts.domain.bookings;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.Instant;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Booking {
    private final Instant creationDate;

    private final Instant bookingDate;

    private final Instant expirationDate;

    private final String email;

    private final int seatsNumber;

    public static Booking createNewBooking(Instant bookingDate, String email, int seatsNumber) {
        if (seatsNumber < 1) {
            throw new BusinessException(String.format("Illegal number of seats in the booking: %d.", seatsNumber));
        }

        if (bookingDate == null) {
            throw new NullPointerException("bookingDate is null");
        }

        final Instant now = Instant.now();
        final Duration expiry = Duration.ofDays(1);
        if (bookingDate.isBefore(now.plus(expiry))) {
            throw new BusinessException("Too late to do the booking for given time");
        }

        checkEmail(email);

        return new Booking(now, bookingDate, bookingDate.minus(expiry), email, seatsNumber);
    }

    private static void checkEmail(String email) {
        // TODO: lame
        final String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        final Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(email).matches()) {
            throw new BusinessException("Invalid email provided.");
        }
    }
}
