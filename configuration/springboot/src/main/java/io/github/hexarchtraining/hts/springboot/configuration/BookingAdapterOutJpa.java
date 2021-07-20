package io.github.hexarchtraining.hts.springboot.configuration;

import io.github.hexarchtraining.hts.bookings.adapter.out.jpa.BookingRepository;
import io.github.hexarchtraining.hts.bookings.adapter.out.jpa.DeleteTableBookingJpaAdapter;
import io.github.hexarchtraining.hts.bookings.adapter.out.jpa.FindBookingByTokenJpaAdapter;
import io.github.hexarchtraining.hts.bookings.adapter.out.jpa.FindFreeTablesJpaAdapter;
import io.github.hexarchtraining.hts.bookings.adapter.out.jpa.FindTableBookingJpaAdapter;
import io.github.hexarchtraining.hts.bookings.adapter.out.jpa.PersistBookingJpaAdapter;
import io.github.hexarchtraining.hts.bookings.adapter.out.jpa.PersistTableBookingJpaAdapter;
import io.github.hexarchtraining.hts.bookings.adapter.out.jpa.SaveBookingJpaAdapter;
import io.github.hexarchtraining.hts.bookings.adapter.out.jpa.TableBookingRepository;
import io.github.hexarchtraining.hts.bookings.adapter.out.jpa.TableRepository;
import io.github.hexarchtraining.hts.bookings.port.out.DeleteTableBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.bookings.port.out.FindFreeTablesPort;
import io.github.hexarchtraining.hts.bookings.port.out.FindTableBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.PersistBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.PersistTableBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.SaveBookingPort;
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
}
