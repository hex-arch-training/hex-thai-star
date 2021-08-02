package io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.BookingEntity;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableBookingEntity;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(uses = {TableBookingMapper.class})
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target="id.value", source="id")
    Booking toDomain(BookingEntity bookingEntity);

    @Mapping(target="id", source="id.value")
    @Mapping(target="tableBookings", ignore = true)
    void toEntity(Booking booking, @MappingTarget BookingEntity bookingEntity);
}
