package io.github.hexarchtraining.hts.order.port.in;

import lombok.NonNull;
import lombok.Value;

@Value
public class CreateOrderLineCommad {

    Long dishId;
    Integer amount;
    String comment;

    public CreateOrderLineCommad(@NonNull Long dishId, @NonNull Integer amount, @NonNull String comment) {
        this.dishId = dishId;
        this.amount = amount;
        this.comment = comment;
    }
}
