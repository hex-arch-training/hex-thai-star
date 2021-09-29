package io.github.hexarchtraining.hts.springboot.configuration;

import io.github.hexarchtraining.hts.booking.port.in.bci.FindBookingByTokenUseCase;
import io.github.hexarchtraining.hts.order.port.out.CreateOrderPort;
import io.github.hexarchtraining.hts.order.port.out.FindBookingForOrderByTokenAdapter;
import io.github.hexarchtraining.hts.order.port.out.FindBookingForOrderByTokenPort;
import io.github.hexarchtraining.hts.order.port.out.jpa.CreateOrderJpaAdapter;
import io.github.hexarchtraining.hts.order.port.out.jpa.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@AllArgsConstructor
@Configuration
public class OrderAdapterOutJpaConfiguration {

    private final FindBookingByTokenUseCase findBookingByTokenUseCase;
    private final OrderRepository orderRepository;

    @Bean
    public FindBookingForOrderByTokenPort findBookingForOrderByTokenPort() {
        return new FindBookingForOrderByTokenAdapter(findBookingByTokenUseCase);
    }

    @Bean
    public CreateOrderPort createOrderPort() {
        return new CreateOrderJpaAdapter(orderRepository);
    }
}
