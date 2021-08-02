package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target="id.value", source="id")
    @Mapping(target="tableBookings", expression = "java(tableBookings)")
    Booking toDomain(BookingRecord bookingRecord, @Context Set<TableBooking> tableBookings);

    @Mapping(target="id", source="id.value")
    BookingRecord toRecord(Booking booking);
}
