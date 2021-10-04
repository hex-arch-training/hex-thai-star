package io.github.hexarchtraining.hts.booking.adapter.in.springweb;

import io.github.hexarchtraining.hts.booking.port.in.CancelBookingCommand;
import io.github.hexarchtraining.hts.booking.port.in.CancelBookingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("booking")
public class CancelBookingController {

  final CancelBookingPort cancelBookingPort;

  @PostMapping(value = "/booking/cancel/{token}")
  public void cancelBooking(@PathVariable String token) {
    final CancelBookingCommand cancelBookingCommand = mapInputToCommand(token);
    cancelBookingPort.cancel(cancelBookingCommand);
  }

  private CancelBookingCommand mapInputToCommand(String token) {
    return new CancelBookingCommand(token);
  }
}

