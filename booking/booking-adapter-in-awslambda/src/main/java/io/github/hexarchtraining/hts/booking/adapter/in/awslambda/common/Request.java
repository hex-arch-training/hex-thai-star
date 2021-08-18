package io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common;

import java.util.Map;
import java.util.Optional;

public class Request {
    private static final String QUERY_PARAMS = "queryStringParameters";
    private static final String PATH_PARAMS = "pathParameters";
    private final Map<String, Object> rawRequest;

    public Request(Map<String, Object> rawRequest) {
        this.rawRequest = rawRequest;
    }

    public Map<String, Object> getValue() {
        return this.rawRequest;
    }

    public Optional<String> getQueryParam(String paramName) {
        if (rawRequest.containsKey(QUERY_PARAMS)) {
            final Map<String, String> queryParams = (Map<String, String>) rawRequest.get(QUERY_PARAMS);
            if (queryParams.containsKey(paramName)) {
                return Optional.of(queryParams.get(paramName));
            }
        }
        return Optional.empty();
    }


    private String getPathParamFromRequest(String paramName) {
        if (rawRequest.containsKey(PATH_PARAMS)) {
            Map<String, String> pathParameters = (Map<String, String>) rawRequest.get(PATH_PARAMS);
            if (pathParameters.containsKey(paramName)) {
                return pathParameters.get(paramName);
            }
        }
        return null;
    }
}
