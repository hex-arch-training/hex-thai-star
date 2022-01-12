package io.github.hexarchtraining.hts.order.adapter.in.springweb;

import lombok.Data;

@Data
public class OrderLineResource {
    Long dishId;
    Integer amount;
    String comment;
}
