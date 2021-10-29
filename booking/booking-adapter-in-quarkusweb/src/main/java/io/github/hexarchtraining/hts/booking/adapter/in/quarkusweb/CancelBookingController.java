package io.github.hexarchtraining.hts.booking.adapter.in.quarkusweb;

import io.github.hexarchtraining.hts.booking.port.in.CancelBookingCommand;
import io.github.hexarchtraining.hts.booking.port.in.CancelBookingPort;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@RequiredArgsConstructor
@Path("booking")
public class CancelBookingController {

    private final CancelBookingPort cancelBookingPort;

    @POST
    @Path("/booking/cancel/{token}")
    public void cancelBooking(@PathParam("token") String token) {
        final CancelBookingCommand cancelBookingCommand = mapInputToCommand(token);
        cancelBookingPort.cancel(cancelBookingCommand);
    }

    private CancelBookingCommand mapInputToCommand(String token) {
        return new CancelBookingCommand(token);
    }
}

