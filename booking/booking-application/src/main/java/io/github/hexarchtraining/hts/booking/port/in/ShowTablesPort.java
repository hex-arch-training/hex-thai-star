package io.github.hexarchtraining.hts.booking.port.in;

import java.util.List;

public interface ShowTablesPort {
    List<ShowTablesResult> showTables(ShowTablesQuery showTablesQuery);
}
