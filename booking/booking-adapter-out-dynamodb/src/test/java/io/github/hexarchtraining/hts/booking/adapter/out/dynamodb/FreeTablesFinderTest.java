package io.github.hexarchtraining.hts.booking.adapter.out.dynamodb;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.domain.TableId;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FreeTablesFinderTest {
    private final Table table1 = Table.builder().id(new TableId(1111111111L)).maxSeats(4).build();
    private final Table table2 = Table.builder().id(new TableId(2222222222L)).maxSeats(6).build();

    private final Instant bookingFrom = Instant.now().plus(Duration.ofHours(36));
    private final Instant bookingTo = bookingFrom.plus(Duration.ofHours(3));
    private final Booking booking;

    public FreeTablesFinderTest() {
        booking = Booking.createNewBooking(
                new BookingId(6394287366L),
                bookingFrom,
                bookingTo,
                "john@example.com",
                2
        );
        booking.addTableBooking(table1);
    }

    @Test
    void findsAllAvailableTablesWhenThereAreNoBookings() {
        // given
        final List<Table> availableTables = new ArrayList<>();
        availableTables.add(table1);
        availableTables.add(table2);
        // when
        final List<Table> freeTables = new FreeTablesFinder(Collections.emptyList(), availableTables)
                .find(Instant.now(), Instant.now().plus(Duration.ofHours(4)));
        // then
        assertEquals(availableTables.size(), freeTables.size());
    }

    @Test
    void findsAllAvailableTablesWhenNoOverlapWithBooking() {
        // given
        final List<Table> availableTables = new ArrayList<>();
        availableTables.add(table1);
        availableTables.add(table2);
        // when
        final FreeTablesFinder finder = new FreeTablesFinder(Collections.singletonList(booking), availableTables);
        final List<Table> freeTables = finder
                .find(bookingTo.plus(Duration.ofDays(1)), bookingTo.plus(Duration.ofDays(2)));
        // then
        assertEquals(availableTables.size(), freeTables.size());
    }

    @Test
    void findsOnlyFreeTableWhenOverlapWithBooking() {
        // given
        final List<Table> availableTables = new ArrayList<>();
        availableTables.add(table1);
        availableTables.add(table2);
        // when
        final FreeTablesFinder finder = new FreeTablesFinder(Collections.singletonList(booking), availableTables);
        final List<Table> freeTables = finder
                .find(bookingFrom.plus(Duration.ofHours(1)), bookingFrom.plus(Duration.ofHours(4)));
        // then
        assertEquals(1, freeTables.size());
        final Table freeTable = freeTables.get(0);
        assertEquals(table2.getId(), freeTable.getId());
    }
}
