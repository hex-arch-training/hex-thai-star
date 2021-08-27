package io.github.hexarchtraining.hts.booking.adapter.out.dynamodb;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.domain.TableId;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FreeTablesFinder {
    final List<Booking> bookings;
    final List<Table> tables;

    public List<Table> find(@NonNull Instant from, @NonNull Instant to) {
        if (to.isBefore(from)) {
            throw new IllegalArgumentException();
        }
        final List<TableId> idsOfTablesAlreadyBooked = findIdsOfTablesAlreadyBooked(from, to);

        return tables.stream()
                .filter(table -> !idsOfTablesAlreadyBooked.contains(table.getId()))
                .collect(Collectors.toList());
    }

    private List<TableId> findIdsOfTablesAlreadyBooked(Instant from, Instant to) {
        return bookings.stream()
                .filter(booking -> from.isBefore(booking.getBookingToTime()) && booking.getBookingFromTime().isBefore(to))
                .flatMap(booking -> booking.getTableBookings().stream().map(TableBooking::getTableId))
                .collect(Collectors.toList());
    }
}
