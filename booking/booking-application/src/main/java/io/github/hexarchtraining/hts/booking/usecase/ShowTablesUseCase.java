package io.github.hexarchtraining.hts.booking.usecase;

import io.github.hexarchtraining.hts.booking.port.in.ShowTablesQuery;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesResult;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesPort;
import io.github.hexarchtraining.hts.booking.port.out.FindTablesPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowTablesUseCase implements ShowTablesPort {

    private final FindTablesPort findTablesPort;

    @Override
    public List<ShowTablesResult> showTables(ShowTablesQuery showTablesQuery) {
        return findTablesPort.findTables().stream()
                .map(table -> new ShowTablesResult(table.getId().getValue(), table.getMaxSeats()))
                .collect(Collectors.toList());
    }
}
