package io.github.hexarchtraining.hts.booking.adapter.in.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;
import io.github.hexarchtraining.hts.booking.port.in.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowBookingsController {
    private final ShowBookingsPort showBookingsPort;

    public Response showTables(Request request) {
        final List<ShowBookingsResult> tables = showBookingsPort.showBookings(new ShowBookingsQuery());
        return Response.builder()
                .setObjectBody(tables)
                .build();
    }
}
