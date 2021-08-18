package io.github.hexarchtraining.hts.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.ShowTablesController;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.domain.TableId;
import io.github.hexarchtraining.hts.booking.service.ShowTablesService;

import java.util.Arrays;

public class ShowTablesRequestHandler extends AbstractRequestHandler {
    final ShowTablesController controller = new ShowTablesController(
            new ShowTablesService(() -> Arrays.asList(
                    Table.builder().id(new TableId(1)).maxSeats(5).build(),
                    Table.builder().id(new TableId(2)).maxSeats(3).build()
            )));

    @Override
    protected Response handleRequest(Request request) {
        return controller.showTables(request);
    }
}
