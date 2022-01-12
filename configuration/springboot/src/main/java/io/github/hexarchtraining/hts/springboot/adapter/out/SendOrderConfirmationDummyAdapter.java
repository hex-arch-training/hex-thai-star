package io.github.hexarchtraining.hts.springboot.adapter.out;

import io.github.hexarchtraining.hts.order.port.out.OrderConfirmationEvent;
import io.github.hexarchtraining.hts.order.port.out.SendOrderConfirmationPort;
import org.springframework.stereotype.Service;

@Service
public class SendOrderConfirmationDummyAdapter implements SendOrderConfirmationPort {
    @Override
    public void sendOrderConfirmation(OrderConfirmationEvent fromOrder) {
        // just to fulfil dependency, normally we would like to dispatch an event to notification component
        System.out.println("event sent " + fromOrder.toString());
    }
}
