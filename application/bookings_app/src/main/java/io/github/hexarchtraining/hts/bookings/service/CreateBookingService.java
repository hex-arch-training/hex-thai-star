package io.github.hexarchtraining.hts.bookings.service;

import io.github.hexarchtraining.hts.bookings.domain.Booking;
import io.github.hexarchtraining.hts.bookings.domain.Table;
import io.github.hexarchtraining.hts.bookings.domain.TableBooking;
import io.github.hexarchtraining.hts.bookings.port.in.CreateBookingCommand;
import io.github.hexarchtraining.hts.bookings.port.in.CreateBookingUseCase;
import io.github.hexarchtraining.hts.bookings.port.out.BookingConfimationEvent;
import io.github.hexarchtraining.hts.bookings.port.out.FindFreeTablesPort;
import io.github.hexarchtraining.hts.bookings.port.out.PersistBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.PersistTableBookingPort;
import io.github.hexarchtraining.hts.bookings.port.out.SendBookingConfirmationPort;
import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CreateBookingService implements CreateBookingUseCase {

    private final PersistBookingPort persistBookingPort;

    private final PersistTableBookingPort persistTableBookingPort;

    private final FindFreeTablesPort findFreeTablesPort;

    private final SendBookingConfirmationPort sendBookingConfirmationPort;

    private final TransactionPort transactionPort;

    @Override
    public void createBooking(CreateBookingCommand command) {
        transactionPort.withTransaction(() -> {
            final Booking booking = Booking.createNewBooking(command.getBookingFrom(), command.getBookingTo(), command.getEmail(), command.getSeatsNumber());
            final Booking bookingPersisted = persistBookingPort.persist(booking);

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

                final TableBooking tableBooking = TableBooking.createTableBooking(bookingPersisted, selectedTable, command.getBookingFrom(), command.getBookingTo());
                persistTableBookingPort.persist(tableBooking);

                sendBookingConfirmationPort.send(BookingConfimationEvent.fromBooking(bookingPersisted, tableBooking));
            }
        });
    }
}
