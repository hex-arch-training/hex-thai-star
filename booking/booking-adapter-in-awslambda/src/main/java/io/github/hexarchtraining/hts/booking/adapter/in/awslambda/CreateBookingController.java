package io.github.hexarchtraining.hts.booking.adapter.in.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.InvalidRequestException;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;
import io.github.hexarchtraining.hts.booking.domain.TableId;
import io.github.hexarchtraining.hts.booking.port.in.*;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class CreateBookingController {
    private final CreateBookingUseCase createBookingUseCase;

    public Response createBooking(Request request) {
        final CreateBookingCommand command = mapToCreateBookingCommand(request);
        final CreateBookingResult result = createBookingUseCase.createBooking(command);
        return Response.builder()
                .setObjectBody(result)
                .build();
    }

    private CreateBookingCommand mapToCreateBookingCommand(Request request) throws InvalidRequestException {
        try {
            final CreateBookingRequestBody requestBody = request.parseBody(CreateBookingRequestBody.class)
                    .orElseThrow(() -> new InvalidRequestException("Empty request body"));
            requestBody.validate();
            return new CreateBookingCommand(requestBody.getBookingFrom(),
                    requestBody.getBookingTo(), requestBody.getEmail(), requestBody.getSeatsNumber(),
                    new TableId(requestBody.getSuggestedTable()));
        } catch (IOException exception) {
            throw new InvalidRequestException("Request body could not be parsed", exception);
        }
    }
}
