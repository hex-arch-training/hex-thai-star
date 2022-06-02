package io.github.hexarchtraining.hts.configuration.quarkusawslambda.configuration;

import io.github.hexarchtraining.hts.booking.adapter.out.dynamodb.FindBookingsDynamoDbAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.dynamodb.FindFreeTablesDynamoDbAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.dynamodb.FindTablesDynamoDbAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.dynamodb.SaveBookingDynamoDbAdapter;
import io.github.hexarchtraining.hts.booking.port.out.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Optional;

/**
 * Adapter configuration for Booking component and JPA framework.
 */
@ApplicationScoped
public class BookingAdapterOutDynamoDbConfiguration {
    private final SaveBookingDynamoDbAdapter saveBookingDynamoDbAdapter = new SaveBookingDynamoDbAdapter();

    @Produces
    public FindBookingByTokenPort findBookingByTokenPort() {
        return token -> Optional.empty();
    }

    @Produces
    public FindFreeTablesPort findFreeTablesPort() {
        return new FindFreeTablesDynamoDbAdapter();
    }

    @Produces
    public PersistBookingPort persistBookingPort() {
        return saveBookingDynamoDbAdapter;
    }

    @Produces
    public SaveBookingPort saveBookingPort() {
        return saveBookingDynamoDbAdapter;
    }

    @Produces
    public FindTablesPort findTablesPort() {
        return new FindTablesDynamoDbAdapter();
    }

    @Produces
    public FindBookingsPort findBookingsPort() {
        return new FindBookingsDynamoDbAdapter();
    }
}
