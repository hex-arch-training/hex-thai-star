package io.github.hexarchtraining.hts.springboot.configuration;

import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import io.github.hexarchtraining.hts.order.port.in.CreateOrderUseCase;
import io.github.hexarchtraining.hts.order.port.out.CreateOrderPort;
import io.github.hexarchtraining.hts.order.port.out.FindBookingForOrderByTokenPort;
import io.github.hexarchtraining.hts.order.port.out.SendOrderConfirmationPort;
import io.github.hexarchtraining.hts.order.service.CreateOrderService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class OrderAdapterInConfiguration {
    private final FindBookingForOrderByTokenPort findBookingForOrderByTokenPort;
    private final TransactionPort transactionPort;
    private final CreateOrderPort createOrderPort;
    private final SendOrderConfirmationPort sendOrderConfirmationPort;

    @Bean
    public CreateOrderUseCase createOrderUseCase() {
        return new CreateOrderService(findBookingForOrderByTokenPort, transactionPort, createOrderPort, sendOrderConfirmationPort);
    }
}
