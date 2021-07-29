package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableRecord;
import io.github.hexarchtraining.hts.booking.domain.Table;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableMapper {

    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    @Mapping(target="id.value", source="id")
    Table toDomain(TableRecord tableRecord);
}
