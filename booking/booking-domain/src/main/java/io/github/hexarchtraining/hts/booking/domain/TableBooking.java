package io.github.hexarchtraining.hts.booking.domain;

import io.github.hexarchtraining.hts.booking.domain.exception.BookingValidationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class TableBooking {
    @Getter
    private final BookingId bookingId;

    @Getter
    private final TableId tableId;

    @Getter
    private final Instant bookingFrom;

    @Getter
    private final Instant bookingTo;

    public static TableBooking createTableBooking(@NonNull Booking booking, @NonNull Table table, @NonNull Instant bookingFrom, @NonNull Instant bookingTo) {
        if (booking.getId() == null) {
            throw new BookingValidationException("Booking is not persisted.");
        }
        if (bookingFrom.isBefore(booking.getBookingDate())) {
            throw new BookingValidationException(String.format("Booking window %tT starts before booking date %tT.", bookingFrom, booking.getBookingDate()));
        }
        if (bookingTo.isBefore(bookingFrom)) {
            throw new BookingValidationException(String.format("Start of the booking window %tT is after end of the booking window %tT.", bookingFrom, bookingTo));
        }
        if (booking.getSeatsNumber() > table.getMaxSeats()) {
            throw new BookingValidationException(String.format("Chosen table (%d) is too small for reservation size (%d).", table.getMaxSeats(), booking.getSeatsNumber()));
        }

        return new TableBooking(booking.getId(), table.getId(), bookingFrom, bookingTo);
    }

}
