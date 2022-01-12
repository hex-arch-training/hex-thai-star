package io.github.hexarchtraining.hts.order.port.out.jpa;

import io.github.hexarchtraining.hts.order.domain.Order;
import io.github.hexarchtraining.hts.order.port.out.CreateOrderPort;
import io.github.hexarchtraining.hts.order.port.out.jpa.entity.OrderEntity;
import io.github.hexarchtraining.hts.order.port.out.jpa.mapper.OrderMapper;
import io.github.hexarchtraining.hts.order.port.out.jpa.repository.OrderLineRepository;
import io.github.hexarchtraining.hts.order.port.out.jpa.repository.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;


@AllArgsConstructor
public class CreateOrderJpaAdapter implements CreateOrderPort {


    private OrderRepository orderRepository;

    private final OrderMapper orderMapper = OrderMapper.INSTANCE;


    @Override
    public Order create(Order newOrder) {
        Optional.of(newOrder.getBookingId()).ifPresent(id -> {
                    throw new IllegalArgumentException("bookingId should be null");
                }
        );

        newOrder.getOrderLines().stream().filter(orderLine -> Optional.of(orderLine.getId()).isPresent()).findAny().ifPresent(o -> {
            throw new IllegalArgumentException("Only new Order Lines are allowed");
        });

        OrderEntity orderEntity = new OrderEntity();
        orderMapper.toEntity(newOrder, orderEntity);
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        return orderMapper.toDomain(savedOrder);
    }
}
