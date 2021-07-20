package io.github.hexarchtraining.hts.booking.adapter.in.springweb;

import io.github.hexarchtraining.hts.booking.domain.TableId;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingCommand;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("booking")
public class CreateBookingController {

  final CreateBookingUseCase createBookingUseCase;

  @PostMapping(value = "/booking")
  public void createBooking(@RequestBody CreateBookingResource createBookingResource) {
    final CreateBookingCommand createBookingCommand = mapInputToCommand(createBookingResource);
    createBookingUseCase.createBooking(createBookingCommand);
  }

  private CreateBookingCommand mapInputToCommand(CreateBookingResource createBookingResource) {
    TableId suggestedTable = Optional.ofNullable(createBookingResource.getSuggestedTable())
        .map(TableId::new)
        .orElse(null);

    return new CreateBookingCommand(
        createBookingResource.getBookingFrom(),
        createBookingResource.getBookingTo(),
        createBookingResource.getEmail(),
        createBookingResource.getSeatsNumber(),
        suggestedTable);
  }
}

