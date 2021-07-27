package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target="id.value", source="id")
    Booking toDomain(BookingRecord bookingRecord);

    @Mapping(target="id", source="id.value")
    BookingRecord toRecord(Booking booking);
}
