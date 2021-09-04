package io.github.hexarchtraining.hts.order.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Order {
    @Getter private final OrderId id;

    public static Order createOrder(OrderId orderId) {
        return new Order(orderId);
    }
}
