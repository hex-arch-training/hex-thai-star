package io.github.hexarchtraining.hts.booking.port.in;

import java.util.List;

public interface ShowTablesUseCase {
    List<ShowTablesResult> showTables(ShowTablesQuery showTablesQuery);
}
