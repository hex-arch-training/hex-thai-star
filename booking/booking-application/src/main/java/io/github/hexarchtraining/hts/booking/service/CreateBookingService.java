package io.github.hexarchtraining.hts.booking.service;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingCommand;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingResult;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingUseCase;
import io.github.hexarchtraining.hts.booking.port.out.BookingConfimationEvent;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.PersistBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingConfirmationPort;
import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CreateBookingService implements CreateBookingUseCase {

    private final PersistBookingPort persistBookingPort;

    private final SaveBookingPort saveBookingPort;

    private final FindFreeTablesPort findFreeTablesPort;

    private final SendBookingConfirmationPort sendBookingConfirmationPort;

    private final TransactionPort transactionPort;

    @Override
    public CreateBookingResult createBooking(CreateBookingCommand command) {
        return transactionPort.inTransaction(() -> {
            final Booking booking = persistBookingPort.persist(
                    Booking.createNewBooking(
                            command.getBookingFrom(),
                            command.getBookingTo(),
                            command.getEmail(),
                            command.getSeatsNumber()));

            final List<Table> freeTables = findFreeTablesPort
                    .find(command.getBookingFrom(), command.getBookingTo()).stream()
                    .sorted(Comparator.comparingInt(Table::getMaxSeats))
                    .filter(table -> table.getMaxSeats() >= command.getSeatsNumber())
                    .collect(Collectors.toList());

            if (!freeTables.isEmpty()) {
                // if there is a table preference, we pick it first; if not, we just take a best match
                final Table selectedTable = command.getSuggestedTable()
                        .flatMap(tableId -> freeTables.stream().filter(table -> table.getId().equals(tableId)).findFirst())
                        .orElse(freeTables.get(0));

                booking.addTableBooking(selectedTable);
                saveBookingPort.save(booking);

                sendBookingConfirmationPort.send(BookingConfimationEvent.fromBooking(booking));

            }
            return new CreateBookingResult(
                    booking.getId().getValue(),
                    booking.getTableBookings().stream().map(tableBooking -> tableBooking.getTableId().getValue()).findFirst().orElse(null),
                    booking.getToken());
        });
    }
}
