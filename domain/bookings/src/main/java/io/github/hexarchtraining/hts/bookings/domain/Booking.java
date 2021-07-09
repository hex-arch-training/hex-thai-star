package io.github.hexarchtraining.hts.bookings.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Booking {
    @Getter
    private final BookingId id;

    @Getter
    private final Instant creationDate;

    @Getter
    private final Instant bookingFromTime;

    @Getter
    private final Instant bookingToTime;

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

    @Getter
    private final String token;

    /**
     * Create new booking instance.
     */
    public static Booking createNewBooking(@NonNull Instant bookingFromTime, @NonNull Instant bookingToTime, @NonNull String email, int seatsNumber) {

        final Instant now = Instant.now();
        final Duration expiry = Duration.ofDays(1);
        final Instant bookingDate = bookingFromTime.truncatedTo(ChronoUnit.DAYS);
        if (bookingDate.isBefore(now.plus(expiry))) {
            throw new BusinessException("Too late to do the booking for given time");
        }

        return new Booking(
                null,
                now,
                bookingDate,
                bookingFromTime,
                bookingToTime,
                bookingDate.minus(expiry),
                email,
                seatsNumber,
                BookingStatus.NEW,
                buildToken(email, "CB_"));
    }

    /**
     * Recreate existing booking instance from its persistence source.
     */
    public static Booking hydrate(@NonNull BookingId id, @NonNull Instant creationDate, @NonNull Instant bookingDate, @NonNull Instant bookingFromTime, @NonNull Instant bookingToTime, @NonNull Instant expirationDate, @NonNull String email, int seatsNumber, @NonNull BookingStatus bookingStatus, @NonNull String token) {
        return new Booking(
                id,
                creationDate,
                bookingDate,
                bookingFromTime,
                bookingToTime,
                expirationDate,
                email,
                seatsNumber,
                bookingStatus,
                token);
    }

    private static String buildToken(String email, String type) {

        // TODO: please refactor this ugly code from original mts
        Instant now = Instant.now();
        LocalDateTime ldt1 = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
        String date = String.format("%04d", ldt1.getYear()) + String.format("%02d", ldt1.getMonthValue())
                + String.format("%02d", ldt1.getDayOfMonth()) + "_";

        String time = String.format("%02d", ldt1.getHour()) + String.format("%02d", ldt1.getMinute())
                + String.format("%02d", ldt1.getSecond());

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update((email + date + time).getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return type + date + sb;
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
     * <p>
     * TODO tricky, maybe we should disallow this; use an use case for this and drop & create new booking instead.
     * But maybe we should just treat it as eventual consistency case?
     */
    public void changeBookingSize(int newSeatsNumber) {
        seatsNumber = newSeatsNumber;
    }
}
