package io.github.hexarchtraining.hts.bookings.service;

import io.github.hexarchtraining.hts.bookings.port.in.ShowTablesQuery;
import io.github.hexarchtraining.hts.bookings.port.in.ShowTablesResult;
import io.github.hexarchtraining.hts.bookings.port.in.ShowTablesUseCase;
import io.github.hexarchtraining.hts.bookings.port.out.FindTablesPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowTablesService implements ShowTablesUseCase {

    private final FindTablesPort findTablesPort;

    @Override
    public List<ShowTablesResult> showTables(ShowTablesQuery showTablesQuery) {
        return findTablesPort.findTables().stream()
                .map(table -> new ShowTablesResult(table.getId().getValue(), table.getMaxSeats()))
                .collect(Collectors.toList());
    }
}
