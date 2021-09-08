package io.github.hexarchtraining.hts.quarkusawslambda.configuration;

import io.github.hexarchtraining.hts.booking.port.in.*;
import io.github.hexarchtraining.hts.booking.port.out.*;
import io.github.hexarchtraining.hts.booking.service.*;
import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import io.github.hexarchtraining.hts.common.port.out.TransactionalMapper;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@AllArgsConstructor
@ApplicationScoped
public class BookingAdapterInConfiguration {

    private final FindBookingByTokenPort findBookingByTokenPort;

    private final SaveBookingPort saveBookingPort;

    private final TransactionPort dummyTransactionPort = new TransactionPort() {
        @Override
        public <T> T inTransaction(TransactionalMapper<T> handler) {
            return handler.accept();
        }
    };

    private final PersistBookingPort persistBookingPort;

    private final FindFreeTablesPort findFreeTablesPort;

    private final FindTablesPort findTablesPort;

    private final FindBookingsPort findBookingsPort;

    @Produces
    public CancelBookingUseCase cancelBookingUseCase() {
        return new CancelBookingService(findBookingByTokenPort, saveBookingPort, dummyTransactionPort);
    }

    @Produces
    public ConfirmBookingUseCase confirmBookingUseCase() {
        return new ConfirmBookingService(findBookingByTokenPort, saveBookingPort);
    }

    @Produces
    public CreateBookingUseCase createBookingUseCase() {
        return new CreateBookingService(persistBookingPort, saveBookingPort, findFreeTablesPort, event -> {
        }, dummyTransactionPort);
    }

    @Produces
    public ShowTablesUseCase showTablesUseCase() {
        return new ShowTablesService(findTablesPort);
    }

    @Produces
    public ShowBookingsUseCase showBookingsUseCase() {
        return new ShowBookingsService(findBookingsPort);
    }
}
