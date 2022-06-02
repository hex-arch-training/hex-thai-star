package io.github.hexarchtraining.hts.order.port.out;

public interface SendOrderConfirmationPort {
    void sendOrderConfirmation(OrderConfirmationEvent fromOrder);
}
