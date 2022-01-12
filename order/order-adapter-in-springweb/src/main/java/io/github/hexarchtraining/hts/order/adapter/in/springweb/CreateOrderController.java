package io.github.hexarchtraining.hts.order.adapter.in.springweb;

import io.github.hexarchtraining.hts.order.port.in.CreateOrderCommand;
import io.github.hexarchtraining.hts.order.port.in.CreateOrderResult;
import io.github.hexarchtraining.hts.order.port.in.CreateOrderUseCase;
import io.github.hexarchtraining.hts.order.port.in.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class CreateOrderController {

    final CreateOrderUseCase createOrderUseCase;

    @PostMapping(value = "/order")
    public CreateOrderResult createOrder(@RequestBody CreateOrderResource createOrderResource) {
        CreateOrderCommand createOrderCommand = mapToCreateOrderCommand(createOrderResource);
        return createOrderUseCase.createOrder(createOrderCommand);
    }

    private CreateOrderCommand mapToCreateOrderCommand(CreateOrderResource createOrderResource) {
        List<OrderLine> orderLines = createOrderResource.getOrderLines().stream().map(orderLineResource -> new OrderLine(orderLineResource.dishId, orderLineResource.amount, orderLineResource.comment)).collect(Collectors.toUnmodifiableList());
        return new CreateOrderCommand(createOrderResource.getToken(), orderLines);
    }
}
