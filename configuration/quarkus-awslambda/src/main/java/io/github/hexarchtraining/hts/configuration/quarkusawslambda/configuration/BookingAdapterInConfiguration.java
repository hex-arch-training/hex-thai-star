package io.github.hexarchtraining.hts.configuration.quarkusawslambda.configuration;

import io.github.hexarchtraining.hts.booking.port.in.CancelBookingUseCase;
import io.github.hexarchtraining.hts.booking.port.in.ConfirmBookingUseCase;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingUseCase;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsUseCase;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesUseCase;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.PersistBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingStatusEventPort;
import io.github.hexarchtraining.hts.booking.application.service.CancelBookingService;
import io.github.hexarchtraining.hts.booking.application.service.ConfirmBookingService;
import io.github.hexarchtraining.hts.booking.application.service.CreateBookingService;
import io.github.hexarchtraining.hts.booking.application.service.SendBookingStatusService;
import io.github.hexarchtraining.hts.booking.application.service.ShowBookingsService;
import io.github.hexarchtraining.hts.booking.application.service.ShowTablesService;
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

    private final SendBookingStatusEventPort sendBookingStatusEventPort;

    @Produces
    public SendBookingStatusService sendBookingStatusUseCase() {
        return new SendBookingStatusService(sendBookingStatusEventPort);
    }

    @Produces
    public CancelBookingUseCase cancelBookingUseCase() {
        return new CancelBookingService(findBookingByTokenPort, saveBookingPort, dummyTransactionPort, sendBookingStatusUseCase());
    }

    @Produces
    public ConfirmBookingUseCase confirmBookingUseCase() {
        return new ConfirmBookingService(findBookingByTokenPort, saveBookingPort, sendBookingStatusUseCase());
    }

    @Produces
    public CreateBookingUseCase createBookingUseCase() {
        return new CreateBookingService(
            persistBookingPort,
            saveBookingPort,
            findFreeTablesPort,
            dummyTransactionPort,
            sendBookingStatusUseCase());
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
