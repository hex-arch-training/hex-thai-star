package io.github.hexarchtraining.hts.booking.application.service;

import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.domain.TableId;
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
                .map(booking -> new ShowBookingsResult(
                        booking.getEmail(),
                        booking.getBookingFromTime(),
                        booking.getBookingToTime(),
                        booking.getTableBookings().stream()
                                .findFirst()
                                .map(TableBooking::getTableId)
                                .map(TableId::getValue)
                                .orElse(null),
                        booking.getSeatsNumber(),
                        booking.getToken(),
                        booking.getStatus().name()))
                .collect(Collectors.toList());
    }
}
