package io.github.hexarchtraining.hts.booking.adapter.in.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.TableId;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingCommand;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingResult;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingUseCase;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateBookingControllerTest {
    @Test
    void parsesRequestBodyAndFormatsResponseOne() throws JSONException {
        // given
        // REQUEST body
        final String email = "john@example.com";
        final int seatsNumber = 4;
        final long suggestedTable = 9999999999L;
        final Instant bookingFrom = Instant.now().plus(Duration.ofDays(2));
        final Instant bookingTo = Instant.now().plus(Duration.ofDays(3));
        final String requestBodyAsJson = "{\"email\":\"".concat(email).concat("\",")
                .concat("\"seatsNumber\":").concat(String.valueOf(seatsNumber)).concat(",")
                .concat("\"suggestedTable\":").concat(String.valueOf(suggestedTable)).concat(",")
                .concat("\"bookingFrom\":\"").concat(bookingFrom.toString()).concat("\",")
                .concat("\"bookingTo\":\"").concat(bookingTo.toString()).concat("\"")
                .concat("}");
        final Request request = new Request(Collections.singletonMap(Request.BODY, requestBodyAsJson));
        // RESPONSE body
        final long bookingId = 1234567891L;
        final long tableId = 9876543219L;
        final String token = "CB_20210826_3651963cd885267f4c186045a0019e60";
        final String expectedResponseBodyAsJson = "{\"bookingId\":".concat(String.valueOf(bookingId)).concat(",")
                .concat("\"tableId\":").concat(String.valueOf(tableId)).concat(",")
                .concat("\"token\":\"").concat(token).concat("\"")
                .concat("}");

        final CreateBookingUseCase useCaseMock = mock(CreateBookingUseCase.class);
        when(useCaseMock.createBooking(any())).thenReturn(new CreateBookingResult(bookingId, tableId, token));
        final CreateBookingController controller = new CreateBookingController(useCaseMock);
        // when
        final Response response = controller.createBooking(request);
        // then
        verify(useCaseMock).createBooking(
                new CreateBookingCommand(bookingFrom, bookingTo, email, seatsNumber, new TableId(suggestedTable)));
        assertEquals(response.getStatusCode(), 200);
        JSONAssert.assertEquals(expectedResponseBodyAsJson, response.getBody(), false);
    }
}
