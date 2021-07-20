package io.github.hexarchtraining.hts.bookings.port.in;

import java.util.List;

public interface ShowTablesUseCase {
    List<ShowTablesResult> showTables(ShowTablesQuery showTablesQuery);
}
