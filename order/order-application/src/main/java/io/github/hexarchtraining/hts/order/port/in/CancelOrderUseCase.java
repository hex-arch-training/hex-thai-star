package io.github.hexarchtraining.hts.order.port.in;

public interface CancelOrderUseCase {
    CancelOrderResult cancelOrder(CancelOrderCommand cancelOrderCommand);
}
