package io.github.hexarchtraining.hts.order.domain;

import io.github.hexarchtraining.hts.order.domain.exception.OrderValidationException;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderLine {
    @Getter
    private final OrderLineId id;

    @Getter
    private final Long dishId;

    @Getter
    private final Integer amount;
    @Getter
    private final String comment;

    public static OrderLine createNewOrderLine(Long dishId, Integer amount, String comment) {
        return createOrderLine(null, dishId, amount, comment);
    }

    public static OrderLine createOrderLine(OrderLineId id, Long dishId, Integer amount, String comment) {
        if (amount == null || amount < 0) {
            throw new OrderValidationException("Negative amount is forbidden");
        }
        return new OrderLine(id, dishId, amount, comment);
    }
}
