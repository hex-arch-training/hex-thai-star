package io.github.hexarchtraining.hts.domain.bookings;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
            throw new BusinessException("Booking is not persisted");
        }
        if (bookingFrom.isBefore(booking.getBookingDate())) {
            throw new BusinessException("Booking window starts before booking date");
        }
        if (bookingTo.isBefore(bookingFrom)) {
            throw new BusinessException("Invalid booking window");
        }
        if (booking.getSeatsNumber() > table.getMaxSeats()) {
            throw new BusinessException("Table is too small for reservation");
        }

        return new TableBooking(booking.getId(), table.getId(), bookingFrom, bookingTo);
    }

}
