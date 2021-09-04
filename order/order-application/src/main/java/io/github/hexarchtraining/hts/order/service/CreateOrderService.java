package io.github.hexarchtraining.hts.order.service;

import io.github.hexarchtraining.hts.order.port.in.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    @Override
    public CreateOrderResult createOrder(CreateOrderCommand createOrderCommand) {
        return null;
    }
}
