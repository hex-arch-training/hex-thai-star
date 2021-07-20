package io.github.hexarchtraining.hts.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "io.github.hexarchtraining.hts.springboot",
       "io.github.hexarchtraining.hts.booking.adapter.in.springweb"})
@EnableJpaRepositories(basePackages = "io.github.hexarchtraining.hts.bookings.adapter.out.jpa")
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
