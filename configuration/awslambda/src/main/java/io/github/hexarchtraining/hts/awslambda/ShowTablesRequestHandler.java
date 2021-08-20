package io.github.hexarchtraining.hts.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.ShowTablesController;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;
import io.github.hexarchtraining.hts.booking.adapter.out.dynamodb.FindTablesDynamoDbAdapter;
import io.github.hexarchtraining.hts.booking.service.ShowTablesService;

public class ShowTablesRequestHandler extends AbstractRequestHandler {
    final ShowTablesController controller = new ShowTablesController(new ShowTablesService(new FindTablesDynamoDbAdapter()));

    @Override
    protected Response handleRequest(Request request) {
        return controller.showTables(request);
    }
}
