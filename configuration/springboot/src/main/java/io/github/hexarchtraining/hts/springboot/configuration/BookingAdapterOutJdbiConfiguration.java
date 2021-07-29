package io.github.hexarchtraining.hts.springboot.configuration;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.DeleteTableBookingJdbiAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.FindBookingByTokenJdbiAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.FindBookingsJdbiAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.FindFreeTablesJdbiAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.FindTableBookingJdbiAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.FindTablesJdbiAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.PersistBookingJdbiAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.PersistTableBookingJdbiAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.SaveBookingJdbiAdapter;
import io.github.hexarchtraining.hts.booking.port.out.DeleteTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.PersistBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.PersistTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.common.adapter.out.jdbi.TransactionJdbiAdapter;
import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
@ConditionalOnProperty(name="booking.store", havingValue = "jdbi")
public class BookingAdapterOutJdbiConfiguration {

    private final Jdbi jdbi;

    @Bean
    public TransactionPort transactionPort() {
        return new TransactionJdbiAdapter(jdbi);
    }

    @Bean
    public DeleteTableBookingPort deleteTableBookingPort() {
        return new DeleteTableBookingJdbiAdapter(jdbi);
    }

    @Bean
    public FindBookingByTokenPort findBookingByTokenPort() {
        return new FindBookingByTokenJdbiAdapter(jdbi);
    }

    @Bean
    public FindFreeTablesPort findFreeTablesPort() {
        return new FindFreeTablesJdbiAdapter(jdbi);
    }

    @Bean
    public FindTableBookingPort findTableBookingPort() {
        return new FindTableBookingJdbiAdapter(jdbi);
    }

    @Bean
    public PersistBookingPort persistBookingPort() {
        return new PersistBookingJdbiAdapter(jdbi);
    }

    @Bean
    public PersistTableBookingPort persistTableBookingPort() {
        return new PersistTableBookingJdbiAdapter(jdbi);
    }

    @Bean
    public SaveBookingPort saveBookingPort() {
        return new SaveBookingJdbiAdapter(jdbi);
    }

    @Bean
    public FindTablesPort findTablesPort() {
        return new FindTablesJdbiAdapter(jdbi);
    }

    @Bean
    public FindBookingsPort findBookingsPort() {
        return new FindBookingsJdbiAdapter(jdbi);
    }

}
