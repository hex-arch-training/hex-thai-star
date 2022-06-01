package io.github.hexarchtraining.hts.order.port.in;

import lombok.NonNull;
import lombok.Value;

@Value
public class OrderLine {

    Long dishId;
    Integer amount;
    String comment;

    public OrderLine(@NonNull Long dishId, @NonNull Integer amount, String comment) {
        this.dishId = dishId;
        this.amount = amount;
        this.comment = comment;
    }

}
