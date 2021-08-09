package io.github.hexarchtraining.hts.booking.adapter.in.quarkusweb;

import io.github.hexarchtraining.hts.booking.port.in.ConfirmBookingCommand;
import io.github.hexarchtraining.hts.booking.port.in.ConfirmBookingUseCase;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RequiredArgsConstructor
@Path("booking")
public class ConfirmBookingController {

  final ConfirmBookingUseCase createBookingUseCase;

  @POST
  @Path("/booking/confirm/{token}")
  public void confirmBooking(@PathParam("token") String token) {
    final ConfirmBookingCommand createBookingCommand = mapInputToCommand(token);
    createBookingUseCase.confirm(createBookingCommand);
  }

  private ConfirmBookingCommand mapInputToCommand(String token) {

    return new ConfirmBookingCommand(token);
  }
}

