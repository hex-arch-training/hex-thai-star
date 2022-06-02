package io.github.hexarchtraining.hts.configuration.quarkus.configuration;

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
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@AllArgsConstructor
@ApplicationScoped
public class BookingAdapterInConfiguration {

    private final FindBookingByTokenPort findBookingByTokenPort;

    private final SaveBookingPort saveBookingPort;

    private final TransactionPort transactionPort;

    private final PersistBookingPort persistBookingPort;

    private final FindFreeTablesPort findFreeTablesPort;

    private final SendBookingStatusEventPort sendBookingStatusEventPort;

    private final FindTablesPort findTablesPort;

    private final FindBookingsPort findBookingsPort;

    @Produces
    public SendBookingStatusService sendBookingStatusUseCase() {
        return new SendBookingStatusService(sendBookingStatusEventPort);
    }

    @Produces
    public CancelBookingUseCase cancelBookingUseCase() {
        return new CancelBookingService(findBookingByTokenPort, saveBookingPort, transactionPort, sendBookingStatusUseCase());
    }

    @Produces
    public ConfirmBookingUseCase confirmBookingUseCase() {
        return new ConfirmBookingService(findBookingByTokenPort, saveBookingPort, sendBookingStatusUseCase());
    }

    @Produces
    public CreateBookingUseCase createBookingUseCase() {
        return new CreateBookingService(persistBookingPort, saveBookingPort, findFreeTablesPort, transactionPort, sendBookingStatusUseCase());
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
