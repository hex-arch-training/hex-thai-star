package io.github.hexarchtraining.hts.order.port.in;

import io.github.hexarchtraining.hts.order.domain.Order;

public interface CreateOrderUseCase {
    CreateOrderResult createOrder(CreateOrderCommand createOrderCommand);
}
