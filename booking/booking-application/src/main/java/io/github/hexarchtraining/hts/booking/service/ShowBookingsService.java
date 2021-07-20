package io.github.hexarchtraining.hts.booking.service;

import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsQuery;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsResult;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsUseCase;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowBookingsService implements ShowBookingsUseCase {

    private final FindBookingsPort findBookingsPort;

    @Override
    public List<ShowBookingsResult> showBookings(ShowBookingsQuery showBookingsQuery) {
        return findBookingsPort.findBookings().stream()
                .map(bookingWithTable -> new ShowBookingsResult(
                        bookingWithTable.getBooking().getEmail(),
                        bookingWithTable.getBooking().getBookingFromTime(),
                        bookingWithTable.getBooking().getBookingToTime(),
                        bookingWithTable.getTableBooking().map(tableBooking -> tableBooking.getTableId().getValue()).orElse(null),
                        bookingWithTable.getBooking().getSeatsNumber(),
                        bookingWithTable.getBooking().getToken(),
                        bookingWithTable.getBooking().getStatus().name()))
                .collect(Collectors.toList());
    }
}
