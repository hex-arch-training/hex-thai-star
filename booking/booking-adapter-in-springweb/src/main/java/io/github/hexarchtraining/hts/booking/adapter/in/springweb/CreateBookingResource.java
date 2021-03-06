package io.github.hexarchtraining.hts.booking.adapter.in.springweb;

import lombok.Data;

import java.time.Instant;

@Data
public class CreateBookingResource {

  Instant bookingFrom;
  Instant bookingTo;
  String email;
  int seatsNumber;
  Long suggestedTable;
}
