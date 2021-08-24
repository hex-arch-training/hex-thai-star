package io.github.hexarchtraining.hts.quarkus.configuration;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.FindBookingByTokenJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.FindBookinsJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.FindFreeTablesJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.FindTablesJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.PersistBookingJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.SaveBookingJpaAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.BookingRepository;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableBookingRepository;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableRepository;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.PersistBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Adapter configuration for Booking component and JPA framework.
 */
@AllArgsConstructor
@ApplicationScoped
public class BookingAdapterOutJpaConfiguration {

    private final TableBookingRepository tableBookingRepository;

    private final BookingRepository bookingRepository;

    private final TableRepository tableRepository;

    @Produces
    public FindBookingByTokenPort findBookingByTokenPort() {
        return new FindBookingByTokenJpaAdapter(bookingRepository);
    }

    @Produces
    public FindFreeTablesPort findFreeTablesPort() {
        return new FindFreeTablesJpaAdapter(tableRepository, tableBookingRepository);
    }

    @Produces
    public PersistBookingPort persistBookingPort() {
        return new PersistBookingJpaAdapter(bookingRepository);
    }

    @Produces
    public SaveBookingPort saveBookingPort() {
        return new SaveBookingJpaAdapter(bookingRepository, tableBookingRepository, tableRepository);
    }

    @Produces
    public FindTablesPort findTablesPort() {
        return new FindTablesJpaAdapter(tableRepository);
    }

    @Produces
    public FindBookingsPort findBookingsPort() {
        return new FindBookinsJpaAdapter(bookingRepository);
    }
}
