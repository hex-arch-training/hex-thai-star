package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableBookingMapper {
    TableBookingMapper INSTANCE = Mappers.getMapper(TableBookingMapper.class);

    @Mapping(target = "bookingId.value", source="bookingId")
    @Mapping(target = "tableId.value", source="tableId")
    TableBooking toDomain(TableBookingRecord tableBookingRecord);

    @Mapping(target = "bookingId", source = "bookingId.value")
    @Mapping(target = "tableId", source = "tableId.value")
    @Mapping(target = "id", ignore = true)
    TableBookingRecord toRecord(TableBooking tableBooking);
}
