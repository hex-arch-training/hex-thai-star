package io.github.hexarchtraining.hts.configuration.awslambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public abstract class AbstractRequestHandler implements RequestHandler<Map<String, Object>, Response> {
    private static final Logger LOG = LogManager.getLogger(AbstractRequestHandler.class);

    @Override
    public Response handleRequest(Map<String, Object> input, Context context) {
        final Response.Builder responseBuilder = new Response.Builder();
        try {
            final Request request = new Request(input);
            return handleRequest(request);
        } catch (Exception exception) {
            responseBuilder
                    .setStatusCode(500)
                    .setObjectBody("Server error");
            LOG.error("Unexpected error when handling request", exception);
        }

        try {
            return responseBuilder.build();
        } catch (Exception exception) {
            LOG.error("Unexpected error when building a response", exception);
            return responseBuilder
                    .setStatusCode(500)
                    .setObjectBody("Server error")
                    .build();
        }
    }

    protected abstract Response handleRequest(Request request);
}
