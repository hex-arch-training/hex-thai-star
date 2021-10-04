package io.github.hexarchtraining.hts.booking.adapter.in.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;
import io.github.hexarchtraining.hts.booking.domain.BookingStatus;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsResult;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsPort;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShowBookingsControllerTest {
    @Test
    void formatsResponseBody() throws JSONException {
        // given
        final long tableId = 9876543219L;
        final String token = "CB_20210826_3651963cd885267f4c186045a0019e60";
        final String email = "john@example.com";
        final int maxSeats = 4;
        final Instant bookingFrom = Instant.now().plus(Duration.ofDays(2));
        final Instant bookingTo = Instant.now().plus(Duration.ofDays(3));
        final String bookingStatus = BookingStatus.NEW.toString();
        final String expectedResponseBodyAsJson = "[{\"email\":\"".concat(email).concat("\",")
                .concat("\"maxSeats\":").concat(String.valueOf(maxSeats)).concat(",")
                .concat("\"tableId\":").concat(String.valueOf(tableId)).concat(",")
                .concat("\"token\":\"").concat(token).concat("\",")
                .concat("\"status\":\"").concat(bookingStatus).concat("\",")
                .concat("\"bookingFrom\":\"").concat(bookingFrom.toString()).concat("\",")
                .concat("\"bookingTo\":\"").concat(bookingTo.toString()).concat("\"")
                .concat("}]");
        final ShowBookingsPort useCaseMock = mock(ShowBookingsPort.class);
        when(useCaseMock.showBookings(any())).thenReturn(Collections.singletonList(
                new ShowBookingsResult(email, bookingFrom, bookingTo, tableId, maxSeats, token, bookingStatus)));
        final ShowBookingsController controller = new ShowBookingsController(useCaseMock);
        // when
        final Response response = controller.showTables(new Request(Collections.emptyMap()));
        // then
        assertEquals(response.getStatusCode(), 200);
        JSONAssert.assertEquals(expectedResponseBodyAsJson, response.getBody(), false);
    }
}
