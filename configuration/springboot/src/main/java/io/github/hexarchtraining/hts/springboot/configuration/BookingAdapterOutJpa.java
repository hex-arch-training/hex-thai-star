package io.github.hexarchtraining.hts.springboot.configuration;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.BookingRepository;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.DeleteTableBookingJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.FindBookingByTokenJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.FindFreeTablesJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.FindTableBookingJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.FindTablesJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.PersistBookingJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.PersistTableBookingJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.SaveBookingJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableBookingRepository;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableRepository;
import io.github.hexarchtraining.hts.booking.port.out.DeleteTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.PersistBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.PersistTableBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Adapter configuration for Booking component and JPA framework.
 */
@AllArgsConstructor
@Configuration
public class BookingAdapterOutJpa {

    private final TableBookingRepository tableBookingRepository;

    private final BookingRepository bookingRepository;

    private final TableRepository tableRepository;

    @Bean
    public DeleteTableBookingPort deleteTableBookingPort() {
        return new DeleteTableBookingJpaAdapter(tableBookingRepository);
    }

    @Bean
    public FindBookingByTokenPort findBookingByTokenPort() {
        return new FindBookingByTokenJpaAdapter(bookingRepository);
    }

    @Bean
    public FindFreeTablesPort findFreeTablesPort() {
        return new FindFreeTablesJpaAdapter(tableRepository);
    }

    @Bean
    public FindTableBookingPort findTableBookingPort() {
        return new FindTableBookingJpaAdapter(tableBookingRepository);
    }

    @Bean
    public PersistBookingPort persistBookingPort() {
        return new PersistBookingJpaAdapter(bookingRepository);
    }

    @Bean
    public PersistTableBookingPort persistTableBookingPort() {
        return new PersistTableBookingJpaAdapter(tableBookingRepository, tableRepository, bookingRepository);
    }

    @Bean
    public SaveBookingPort saveBookingPort() {
        return new SaveBookingJpaAdapter(bookingRepository);
    }

    @Bean
    public FindTablesPort findTablesPort() {
        return new FindTablesJpaAdapter(tableRepository);
    }
}
