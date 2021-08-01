package io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableBookingEntity;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.domain.TableId;

public class TableBookingMapper {

    public static final TableBookingMapper INSTANCE = new TableBookingMapper();

    public TableBooking toDomain(TableBookingEntity tableBookingEntity) {
        return TableBooking.builder()
                .tableId(new TableId(tableBookingEntity.getTable().getId()))
                .seatsNumber(tableBookingEntity.getSeatsNumber())
                .build();
    }

    public void toEntity(TableBooking tableBooking, TableBookingEntity entity) {
        entity.setSeatsNumber(tableBooking.getSeatsNumber());
    }
}
