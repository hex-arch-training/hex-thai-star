package io.github.hexarchtraining.hts.booking.adapter.in.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.InvalidRequestException;
import lombok.Data;

import java.time.Instant;

@Data
public class CreateBookingRequestBody {
    private Instant bookingFrom;
    private Instant bookingTo;
    private String email;
    private int seatsNumber;
    private Long suggestedTable;

    public void validate() throws InvalidRequestException {
        if (bookingFrom == null) {
            throw new InvalidRequestException("bookingFrom is missing in request body");
        }
        if (bookingTo == null) {
            throw new InvalidRequestException("bookingTo is missing in request body");
        }
        if (email == null) {
            throw new InvalidRequestException("email is missing in request body");
        }

    }
}
