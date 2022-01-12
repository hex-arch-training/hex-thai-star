package io.github.hexarchtraining.hts.order.service;

import io.github.hexarchtraining.hts.order.port.in.CancelOrderCommand;
import io.github.hexarchtraining.hts.order.port.in.CancelOrderResult;
import io.github.hexarchtraining.hts.order.port.in.CancelOrderUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CancelOrderService implements CancelOrderUseCase {

    @Override
    public CancelOrderResult cancelOrder(CancelOrderCommand cancelOrderCommand) {
        return null;
    }
}
