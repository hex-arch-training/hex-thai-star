package io.github.hexarchtraining.hts.order.service;

import io.github.hexarchtraining.hts.order.port.in.CreateOrderLineCommad;
import io.github.hexarchtraining.hts.order.port.in.CreateOrderLineResult;
import io.github.hexarchtraining.hts.order.port.in.CreateOrderLineUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateOrderLineService implements CreateOrderLineUseCase {

    @Override
    public CreateOrderLineResult createOrderLine(CreateOrderLineCommad createOrderLineCommand) {
        return null;
    }
}
