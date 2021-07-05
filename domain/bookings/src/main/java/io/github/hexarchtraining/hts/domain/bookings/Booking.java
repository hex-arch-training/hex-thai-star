package io.github.hexarchtraining.hts.domain.bookings;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.Duration;
import java.time.Instant;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Booking {
    @Getter
    private final BookingId id;

    @Getter
    private final Instant creationDate;

    @Getter
    private final Instant bookingDate;

    @Getter
    private final Instant expirationDate;

    @Getter
    private final String email;

    @Getter
    private int seatsNumber;

    @Getter
    private BookingStatus status;

    /**
     * Create new booking instance.
    */
    public static Booking createNewBooking(@NonNull Instant bookingDate, @NonNull String email, int seatsNumber) {

        final Instant now = Instant.now();
        final Duration expiry = Duration.ofDays(1);
        if (bookingDate.isBefore(now.plus(expiry))) {
            throw new BusinessException("Too late to do the booking for given time");
        }

        return new Booking(null, now, bookingDate, bookingDate.minus(expiry), email, seatsNumber, BookingStatus.NEW);
    }

    /**
     * Recreate existing booking instance from its persistence source.
     */
    public static Booking hydrate(@NonNull BookingId id, @NonNull Instant creationDate, @NonNull Instant bookingDate, @NonNull Instant expirationDate, @NonNull String email, int seatsNumber, @NonNull BookingStatus bookingStatus) {
        return new Booking(id, creationDate, bookingDate, expirationDate, email, seatsNumber, bookingStatus);
    }

    /**
     * Cancels booking.
     */
    public void cancel() {
        if (status == BookingStatus.NEW || status == BookingStatus.CONFIRMED) {
            status = BookingStatus.CANCELLED;
        } else {
            throw new BusinessException(String.format("A booking with status %s cannot be cancelled", status.name()));
        }
    }

    /**
     * Confirms booking.
     */
    public void confirm() {
        if (status == BookingStatus.NEW) {
            status = BookingStatus.CONFIRMED;
        } else {
            throw new BusinessException(String.format("A booking with status %s cannot be confirmed", status.name()));
        }
    }

    /**
     * Change number of seats in the booking.
     *
     * TODO tricky, maybe we should disallow this; use an use case for this and drop & create new booking instead.
     * But maybe we should just treat it as eventual consistency case?
     */
    public void changeBookingSize(int newSeatsNumber) {
        seatsNumber = newSeatsNumber;
    }
}
