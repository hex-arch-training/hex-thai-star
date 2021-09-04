package io.github.hexarchtraining.hts.order.service;

import io.github.hexarchtraining.hts.order.port.in.ShowOrderResult;
import io.github.hexarchtraining.hts.order.port.in.ShowOrderUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowOrderService implements ShowOrderUseCase {

    @Override
    public ShowOrderResult showOrder(long orderId) {
        return null;
    }
}
