package io.github.hexarchtraining.hts.order.adapter.in.springweb;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class CreateOrderResource {
    String token;
    List<OrderLineResource> orderLines = new ArrayList<>();
}
