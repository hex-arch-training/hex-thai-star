package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class BookingWithTables {
    @Getter
    private final BookingRecord booking;
    @Getter
    private final Set<TableBookingRecord> tableBookings = new HashSet<>();

    public void add(TableBookingRecord tableBookingRecord) {
        tableBookings.add(tableBookingRecord);
    }
}
