package io.github.hexarchtraining.hts.configuration.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.HealthCheckController;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;

public class HealthCheckRequestHandler extends AbstractRequestHandler {
    final HealthCheckController controller = new HealthCheckController();

    @Override
    protected Response handleRequest(Request request) {
        return controller.check(request);
    }
}
