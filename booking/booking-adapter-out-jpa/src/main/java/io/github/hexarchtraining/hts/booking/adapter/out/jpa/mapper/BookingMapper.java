package io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.BookingEntity;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {TableBookingMapper.class})
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target="id.value", source="id")
    @Mapping(target = "tableBookings", ignore = true)
    Booking toDomain(BookingEntity bookingEntity);

    @Mapping(target="id", source="id.value")
    void toEntity(Booking booking, @MappingTarget BookingEntity bookingEntity);
}
