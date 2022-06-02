package io.github.hexarchtraining.hts.configuration.springboot.configuration;

import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.enums.EnumStrategy;
import org.jdbi.v3.core.enums.Enums;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@AllArgsConstructor
@Configuration
@ConditionalOnProperty(name = "booking.store", havingValue = "jdbi")
public class JdbiConfiguration {

    private final DataSource dataSource;

    @Bean
    public Jdbi jdbi() {
        final Jdbi jdbi = Jdbi.create(dataSource)
                .installPlugin(new SqlObjectPlugin());
        jdbi.configure(Enums.class,
                enums -> enums.setEnumStrategy(EnumStrategy.BY_ORDINAL));
        return jdbi;
    }
}
