package io.github.hexarchtraining.hts.quarkus.configuration;

import io.github.hexarchtraining.hts.booking.port.in.CancelBookingPort;
import io.github.hexarchtraining.hts.booking.port.in.ConfirmBookingPort;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingPort;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsPort;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.PersistBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingStatusEventPort;
import io.github.hexarchtraining.hts.booking.usecase.CancelBookingUseCase;
import io.github.hexarchtraining.hts.booking.usecase.ConfirmBookingUseCase;
import io.github.hexarchtraining.hts.booking.usecase.CreateBookingUseCase;
import io.github.hexarchtraining.hts.booking.usecase.SendBookingStatusUseCase;
import io.github.hexarchtraining.hts.booking.usecase.ShowBookingsUseCase;
import io.github.hexarchtraining.hts.booking.usecase.ShowTablesUseCase;
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
    public SendBookingStatusUseCase sendBookingStatusUseCase() {
        return new SendBookingStatusUseCase(sendBookingStatusEventPort);
    }

    @Produces
    public CancelBookingPort cancelBookingUseCase() {
        return new CancelBookingUseCase(findBookingByTokenPort, saveBookingPort, transactionPort, sendBookingStatusUseCase());
    }

    @Produces
    public ConfirmBookingPort confirmBookingUseCase() {
        return new ConfirmBookingUseCase(findBookingByTokenPort, saveBookingPort, sendBookingStatusUseCase());
    }

    @Produces
    public CreateBookingPort createBookingUseCase() {
        return new CreateBookingUseCase(persistBookingPort, saveBookingPort, findFreeTablesPort, transactionPort, sendBookingStatusUseCase());
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
