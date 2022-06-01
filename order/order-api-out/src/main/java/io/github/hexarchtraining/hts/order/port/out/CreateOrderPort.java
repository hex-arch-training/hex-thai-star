package io.github.hexarchtraining.hts.order.port.out;

import io.github.hexarchtraining.hts.order.domain.Order;

public interface CreateOrderPort {
    Order create(Order newOrder);
}
