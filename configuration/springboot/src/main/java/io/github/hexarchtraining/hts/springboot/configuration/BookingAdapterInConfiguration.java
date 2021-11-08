package io.github.hexarchtraining.hts.springboot.configuration;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class BookingAdapterInConfiguration {

    private final FindBookingByTokenPort findBookingByTokenPort;

    private final SaveBookingPort saveBookingPort;

    private final TransactionPort transactionPort;

    private final PersistBookingPort persistBookingPort;

    private final FindFreeTablesPort findFreeTablesPort;

    private final SendBookingStatusEventPort sendBookingStatusEventPort;

    private final FindTablesPort findTablesPort;

    private final FindBookingsPort findBookingsPort;

    @Bean
    public SendBookingStatusUseCase sendBookingStatusUseCase() {
        return new SendBookingStatusUseCase(sendBookingStatusEventPort);
    }

    @Bean
    public CancelBookingPort cancelBookingUseCase() {
        return new CancelBookingUseCase(findBookingByTokenPort, saveBookingPort, transactionPort, sendBookingStatusUseCase());
    }

    @Bean
    public ConfirmBookingPort confirmBookingUseCase() {
        return new ConfirmBookingUseCase(findBookingByTokenPort, saveBookingPort, sendBookingStatusUseCase());
    }

    @Bean
    public CreateBookingPort createBookingUseCase() {
        return new CreateBookingUseCase(persistBookingPort, saveBookingPort, findFreeTablesPort, transactionPort, sendBookingStatusUseCase());
    }

    @Bean
    public ShowTablesPort showTablesUseCase() {
        return new ShowTablesUseCase(findTablesPort);
    }

    @Bean
    public ShowBookingsPort showBookingsUseCase() {
        return new ShowBookingsUseCase(findBookingsPort);
    }
}
