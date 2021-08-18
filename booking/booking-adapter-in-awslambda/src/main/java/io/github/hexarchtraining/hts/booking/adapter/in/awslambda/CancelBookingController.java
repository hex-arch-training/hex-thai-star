package io.github.hexarchtraining.hts.booking.adapter.in.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;

import java.util.Collections;

public class CancelBookingController {
    public Response cancel(Request request) {
        return Response.builder()
                .setStatusCode(200)
                .setObjectBody(Collections.singletonMap("status", "done")).build();
    }
}
