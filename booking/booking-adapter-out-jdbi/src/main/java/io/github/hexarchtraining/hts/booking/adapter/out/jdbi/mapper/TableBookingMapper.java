package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.domain.TableId;

public class TableBookingMapper {

    public static final TableBookingMapper INSTANCE = new TableBookingMapper();

    public TableBooking toDomain(TableBookingRecord tableBookingRecord) {
        return TableBooking.builder()
                .tableId(new TableId(tableBookingRecord.getTableId()))
                .seatsNumber(tableBookingRecord.getSeatsNumber())
                .build();
    }

    public TableBookingRecord toRecord(TableBooking tableBooking) {
        final TableBookingRecord record = new TableBookingRecord();
        record.setSeatsNumber(tableBooking.getSeatsNumber());
        return record;
    }
}
