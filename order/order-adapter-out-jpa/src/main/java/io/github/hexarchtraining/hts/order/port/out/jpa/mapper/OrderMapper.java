package io.github.hexarchtraining.hts.order.port.out.jpa.mapper;

import io.github.hexarchtraining.hts.order.domain.Order;
import io.github.hexarchtraining.hts.order.port.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {OrderLineMapper.class})
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target="id.value", source = "id")
    Order toDomain(OrderEntity orderEntity);

    @Mapping(target = "id", source="id.value")
    void toEntity(Order order, @MappingTarget OrderEntity orderEntity);
}
