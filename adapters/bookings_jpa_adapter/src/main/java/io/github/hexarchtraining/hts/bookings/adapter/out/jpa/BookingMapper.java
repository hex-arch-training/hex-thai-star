package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import io.github.hexarchtraining.hts.bookings.domain.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target="id.value", source="id")
    Booking toDomain(BookingEntity bookingEntity);

    @Mapping(target="id", source="id.value")
    void toEntity(Booking booking, @MappingTarget BookingEntity bookingEntity);
}
