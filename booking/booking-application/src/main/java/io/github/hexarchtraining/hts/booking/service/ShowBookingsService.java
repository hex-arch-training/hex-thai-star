package io.github.hexarchtraining.hts.booking.service;

import io.github.hexarchtraining.hts.booking.domain.TableId;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsQuery;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsResult;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsUseCase;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowBookingsService implements ShowBookingsUseCase {

    private final FindBookingsPort findBookingsPort;

    @Override
    public List<ShowBookingsResult> showBookings(ShowBookingsQuery showBookingsQuery) {
        return findBookingsPort.findBookings().stream()
                .map(entity -> new ShowBookingsResult(
                        entity.getBookingFrom(),
                        entity.getBookingTo(),
                        Optional.ofNullable(entity.getTableId()).map(TableId::getValue).orElse(null)))
                .collect(Collectors.toList());
    }
}
