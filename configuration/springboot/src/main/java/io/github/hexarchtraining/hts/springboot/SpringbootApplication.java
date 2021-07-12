package io.github.hexarchtraining.hts.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = SpringbootApplication.APPLICATION_BASE_PACKAGE)
public class SpringbootApplication {

    public static final String APPLICATION_BASE_PACKAGE = "io.github.hexarchtraining.hts";

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
