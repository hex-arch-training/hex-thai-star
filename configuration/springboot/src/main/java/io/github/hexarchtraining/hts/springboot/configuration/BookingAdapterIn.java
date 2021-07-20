package io.github.hexarchtraining.hts.springboot.configuration;

import io.github.hexarchtraining.hts.bookings.port.in.CancelBookingUseCase;
import io.github.hexarchtraining.hts.bookings.port.in.ConfirmBookingUseCase;
import io.github.hexarchtraining.hts.bookings.port.in.CreateBookingUseCase;
import io.github.hexarchtraining.hts.bookings.port.in.ShowTablesUseCase;
import io.github.hexarchtraining.hts.bookings.port.out.DeleteTableBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.FindBookingByTokenPort;
import io.github.hexarchtraining.hts.bookings.port.out.FindFreeTablesPort;
import io.github.hexarchtraining.hts.bookings.port.out.FindTableBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.FindTablesPort;
import io.github.hexarchtraining.hts.bookings.port.out.PersistBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.PersistTableBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.SendBookingConfirmationPort;
import io.github.hexarchtraining.hts.bookings.service.CancelBookingService;
import io.github.hexarchtraining.hts.bookings.service.ConfirmBookingService;
import io.github.hexarchtraining.hts.bookings.service.CreateBookingService;
import io.github.hexarchtraining.hts.bookings.service.ShowTablesService;
import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class BookingAdapterIn {

    private final FindBookingByTokenPort findBookingByTokenPort;

    private final FindTableBookingPort findTableBookingPort;

    private final SaveBookingPort saveBookingPort;

    private final DeleteTableBookingPort deleteTableBookingPort;

    private final TransactionPort transactionPort;

    private final PersistBookingPort persistBookingPort;

    private final PersistTableBookingPort persistTableBookingPort;

    private final FindFreeTablesPort findFreeTablesPort;

    private final SendBookingConfirmationPort sendBookingConfirmationPort;

    private final FindTablesPort findTablesPort;

    @Bean
    public CancelBookingUseCase cancelBookingUseCase() {
        return new CancelBookingService(findBookingByTokenPort, findTableBookingPort, saveBookingPort, deleteTableBookingPort, transactionPort);
    }

    @Bean
    public ConfirmBookingUseCase confirmBookingUseCase() {
        return new ConfirmBookingService(findBookingByTokenPort, findTableBookingPort, saveBookingPort);
    }

    @Bean
    public CreateBookingUseCase createBookingUseCase() {
        return new CreateBookingService(persistBookingPort, persistTableBookingPort, findFreeTablesPort, sendBookingConfirmationPort, transactionPort);
    }

    @Bean
    public ShowTablesUseCase showTablesUseCase() {
        return new ShowTablesService(findTablesPort);
    }
}
