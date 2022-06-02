package io.github.hexarchtraining.hts.configuration.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "io.github.hexarchtraining.hts.springboot",
        "io.github.hexarchtraining.hts.booking.adapter.in.springweb",
        "io.github.hexarchtraining.hts.order.adapter.in.springweb",
        "io.github.hexarchtraining.hts.booking.adapter.out.springmail"})
@EnableJpaRepositories(basePackages = {"io.github.hexarchtraining.hts.booking.adapter.out.jpa", "io.github.hexarchtraining.hts.order.port.out.jpa"})
@EntityScan(basePackages = {"io.github.hexarchtraining.hts.booking.adapter.out.jpa", "io.github.hexarchtraining.hts.order.port.out.jpa"})
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
