package io.github.hexarchtraining.hts.quarkusawslambda.configuration;

import io.github.hexarchtraining.hts.booking.port.in.CancelBookingPort;
import io.github.hexarchtraining.hts.booking.port.in.ConfirmBookingPort;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingPort;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsPort;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.*;
import io.github.hexarchtraining.hts.booking.usecase.*;
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
    public CancelBookingPort cancelBookingUseCase() {
        return new CancelBookingUseCase(findBookingByTokenPort, saveBookingPort, dummyTransactionPort);
    }

    @Produces
    public ConfirmBookingPort confirmBookingUseCase() {
        return new ConfirmBookingUseCase(findBookingByTokenPort, saveBookingPort);
    }

    @Produces
    public CreateBookingPort createBookingUseCase() {
        return new CreateBookingUseCase(persistBookingPort, saveBookingPort, findFreeTablesPort, event -> {
        }, dummyTransactionPort);
    }

    @Produces
    public ShowTablesPort showTablesUseCase() {
        return new ShowTablesUseCase(findTablesPort);
    }

    @Produces
    public ShowBookingsPort showBookingsUseCase() {
        return new ShowBookingsUseCase(findBookingsPort);
    }
}
