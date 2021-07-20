package io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableBookingEntity;
import io.github.hexarchtraining.hts.bookings.domain.TableBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableBookingMapper {

    TableBookingMapper INSTANCE = Mappers.getMapper(TableBookingMapper.class);

    @Mapping(target = "bookingId.value", source="booking.id")
    @Mapping(target = "tableId.value", source="table.id")
    TableBooking toDomain(TableBookingEntity tableBookingEntity);

    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "table", ignore = true)
    @Mapping(target = "id", ignore = true)
    void toEntity(TableBooking tableBooking, @MappingTarget TableBookingEntity tableBookingEntity);
}
