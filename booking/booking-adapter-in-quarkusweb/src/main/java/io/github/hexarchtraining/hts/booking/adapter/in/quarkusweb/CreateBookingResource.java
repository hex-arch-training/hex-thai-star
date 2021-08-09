package io.github.hexarchtraining.hts.booking.adapter.in.quarkusweb;

import lombok.Data;

import java.time.Instant;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
@Data
public class CreateBookingResource {

  Instant bookingFrom;
  Instant bookingTo;
  String email;
  int seatsNumber;
  Long suggestedTable;
}
