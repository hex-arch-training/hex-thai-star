package io.github.hexarchtraining.hts.booking.adapter.in.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;

import java.util.Collections;

public class HealthCheckController {
    public Response check(Request request) {
        return Response.builder()
                .setObjectBody(Collections.singletonMap("input", request.getValue()))
                .setStatusCode(200)
                .build();
    }
}
