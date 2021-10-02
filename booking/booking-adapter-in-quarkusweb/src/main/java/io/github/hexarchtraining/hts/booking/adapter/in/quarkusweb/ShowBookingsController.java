package io.github.hexarchtraining.hts.booking.adapter.in.quarkusweb;

import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsQuery;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsResult;
import io.github.hexarchtraining.hts.booking.port.in.ShowBookingsPort;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@RequiredArgsConstructor
@Path("booking")
public class ShowBookingsController {

    private final ShowBookingsPort showBookingsPort;

    @GET
    @Path("/bookings")
    public List<ShowBookingsResult> showBookings() {
        // in the future, ShowBookingsQuery may contain something interesting
        return showBookingsPort.showBookings(new ShowBookingsQuery());
    }
}
