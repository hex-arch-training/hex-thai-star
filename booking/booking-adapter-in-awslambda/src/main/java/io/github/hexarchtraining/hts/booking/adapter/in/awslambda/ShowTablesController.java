package io.github.hexarchtraining.hts.booking.adapter.in.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesQuery;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesResult;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowTablesController {
    private final ShowTablesPort showTablesPort;

    public Response showTables(Request request) {
        final List<ShowTablesResult> tables = showTablesPort.showTables(new ShowTablesQuery());
        return Response.builder()
                .setObjectBody(tables)
                .build();
    }
}
