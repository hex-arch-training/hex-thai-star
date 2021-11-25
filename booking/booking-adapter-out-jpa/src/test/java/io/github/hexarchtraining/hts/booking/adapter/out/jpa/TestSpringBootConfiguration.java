package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = TestSpringBootConfiguration.BASE_PACKAGE)
@EnableJpaRepositories(basePackages = TestSpringBootConfiguration.BASE_PACKAGE)
@EntityScan(basePackages = TestSpringBootConfiguration.BASE_PACKAGE)
class TestSpringBootConfiguration {
  public static final String BASE_PACKAGE = "io.github.hexarchtraining.hts.booking.adapter.out.jpa";
}