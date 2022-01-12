package io.github.hexarchtraining.hts.order.port.out.jpa.mapper;

import io.github.hexarchtraining.hts.order.domain.OrderLine;
import io.github.hexarchtraining.hts.order.domain.OrderLineId;
import io.github.hexarchtraining.hts.order.port.out.jpa.entity.OrderLineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderLineMapper {

    OrderLineMapper INSTANCE = Mappers.getMapper(OrderLineMapper.class);

    @Mapping(target="id.value", source = "id")
    OrderLine toOrderLine(OrderLineEntity orderLineEntity);

    @Mapping(target = "id", source="id.value")
    void toOrderLineEntity(OrderLine source, @MappingTarget OrderLineEntity target);

    default Long map(OrderLineId value) {
        return value.getValue();
    }
}
