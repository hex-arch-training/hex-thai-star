package io.github.hexarchtraining.hts.order.port.in;

public interface CreateOrderLineUseCase {
    CreateOrderLineResult createOrderLine(CreateOrderLineCommad createOrderLineCommand);
}
