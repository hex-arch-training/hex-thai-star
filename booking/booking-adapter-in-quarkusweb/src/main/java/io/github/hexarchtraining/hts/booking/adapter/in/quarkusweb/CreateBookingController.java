package io.github.hexarchtraining.hts.booking.adapter.in.quarkusweb;

import io.github.hexarchtraining.hts.booking.domain.TableId;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingCommand;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingPort;
import io.github.hexarchtraining.hts.booking.port.in.CreateBookingResult;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Optional;

@RequiredArgsConstructor
@Path("booking")
public class CreateBookingController {

    private final CreateBookingPort createBookingPort;

    @POST
    @Path("/booking")
    public CreateBookingResult createBooking(CreateBookingResource createBookingResource) {
        final CreateBookingCommand createBookingCommand = mapInputToCommand(createBookingResource);
        return createBookingPort.createBooking(createBookingCommand);
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

