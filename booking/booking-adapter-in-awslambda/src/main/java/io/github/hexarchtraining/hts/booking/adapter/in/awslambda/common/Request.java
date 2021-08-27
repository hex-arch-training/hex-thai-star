package io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class Request {
    public static final String QUERY_PARAMS = "queryStringParameters";
    public static final String PATH_PARAMS = "pathParameters";
    public static final String BODY = "body";

    private final ObjectMapper objectMapper;
    private final Map<String, Object> rawRequest;

    public Request(Map<String, Object> rawRequest) {
        this.rawRequest = rawRequest;
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public <T> Optional<T> parseBody(Class<T> type) throws IOException {
        if (rawRequest.containsKey(BODY)) {
            final Object rawBody = rawRequest.get(BODY);
            if (rawBody instanceof String) {
                return Optional.of(objectMapper.readValue((String) rawBody, type));
            }
        }
        return Optional.empty();
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

    public Optional<String> getPathParamFromRequest(String paramName) {
        if (rawRequest.containsKey(PATH_PARAMS)) {
            Map<String, String> pathParameters = (Map<String, String>) rawRequest.get(PATH_PARAMS);
            if (pathParameters.containsKey(paramName)) {
                return Optional.of(pathParameters.get(paramName));
            }
        }
        return Optional.empty();
    }
}
