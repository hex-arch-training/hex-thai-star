package io.github.hexarchtraining.hts.application.bookings.service;

import io.github.hexarchtraining.hts.application.bookings.port.in.CreateBookingCommand;
import io.github.hexarchtraining.hts.application.bookings.port.in.CreateBookingUseCase;
import io.github.hexarchtraining.hts.application.bookings.port.out.FindFreeTablesPort;
import io.github.hexarchtraining.hts.application.bookings.port.out.PersistBookingPort;
import io.github.hexarchtraining.hts.application.bookings.port.out.PersistTableBookingPort;
import io.github.hexarchtraining.hts.domain.bookings.Booking;
import io.github.hexarchtraining.hts.domain.bookings.BusinessException;
import io.github.hexarchtraining.hts.domain.bookings.Table;
import io.github.hexarchtraining.hts.domain.bookings.TableBooking;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CreateBookingService implements CreateBookingUseCase {

    private final PersistBookingPort persistBookingPort;

    private final PersistTableBookingPort persistTableBookingPort;

    private final FindFreeTablesPort findFreeTablesPort;

    @Override
    public void createBooking(CreateBookingCommand command) {

        final List<Table> freeTables = findFreeTablesPort
                .find(command.getBookingFrom(), command.getBookingTo()).stream()
                .sorted(Comparator.comparingInt(Table::getMaxSeats))
                .filter(table -> table.getMaxSeats() >= command.getSeatsNumber())
                .collect(Collectors.toList());

        if (freeTables.size() == 0) {
            throw new BusinessException("Cannot find free table of given size");
        }

        // if there is a table preference, we pick it first; if not, we just take a best match
        final Table selectedTable = command.getSuggestedTable()
                .flatMap(tableId -> freeTables.stream().filter(table -> table.getId().equals(tableId)).findFirst())
                .orElse(freeTables.get(0));

        final Instant bookingDate = command.getBookingFrom().truncatedTo(ChronoUnit.DAYS);

        final Booking booking = Booking.createNewBooking(bookingDate, command.getEmail(), command.getSeatsNumber());
        final Booking bookingPersisted = persistBookingPort.persist(booking);

        final TableBooking tableBooking = TableBooking.createTableBooking(bookingPersisted, selectedTable, command.getBookingFrom(), command.getBookingTo());
        persistTableBookingPort.persist(tableBooking);
    }
}
