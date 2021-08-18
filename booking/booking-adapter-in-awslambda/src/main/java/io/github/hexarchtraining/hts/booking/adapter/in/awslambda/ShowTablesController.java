package io.github.hexarchtraining.hts.booking.adapter.in.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesQuery;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesResult;
import io.github.hexarchtraining.hts.booking.port.in.ShowTablesUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowTablesController {
    private final ShowTablesUseCase showTablesUseCase;

    public Response showTables(Request request) {
        final List<ShowTablesResult> tables = showTablesUseCase.showTables(new ShowTablesQuery());
        return Response.builder()
                .setObjectBody(tables)
                .build();
    }
}
