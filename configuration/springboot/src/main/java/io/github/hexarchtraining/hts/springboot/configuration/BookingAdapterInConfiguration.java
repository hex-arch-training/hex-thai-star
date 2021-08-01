package io.github.hexarchtraining.hts.springboot.configuration;

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
import io.github.hexarchtraining.hts.booking.port.out.SendBookingConfirmationPort;
import io.github.hexarchtraining.hts.booking.service.CancelBookingService;
import io.github.hexarchtraining.hts.booking.service.ConfirmBookingService;
import io.github.hexarchtraining.hts.booking.service.CreateBookingService;
import io.github.hexarchtraining.hts.booking.service.ShowBookingsService;
import io.github.hexarchtraining.hts.booking.service.ShowTablesService;
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

    private final SendBookingConfirmationPort sendBookingConfirmationPort;

    private final FindTablesPort findTablesPort;

    private final FindBookingsPort findBookingsPort;

    @Bean
    public CancelBookingUseCase cancelBookingUseCase() {
        return new CancelBookingService(findBookingByTokenPort, saveBookingPort, transactionPort);
    }

    @Bean
    public ConfirmBookingUseCase confirmBookingUseCase() {
        return new ConfirmBookingService(findBookingByTokenPort, saveBookingPort);
    }

    @Bean
    public CreateBookingUseCase createBookingUseCase() {
        return new CreateBookingService(persistBookingPort, saveBookingPort, findFreeTablesPort, sendBookingConfirmationPort, transactionPort);
    }

    @Bean
    public ShowTablesUseCase showTablesUseCase() {
        return new ShowTablesService(findTablesPort);
    }

    @Bean
    public ShowBookingsUseCase showBookingsUseCase() {
        return new ShowBookingsService(findBookingsPort);
    }
}
