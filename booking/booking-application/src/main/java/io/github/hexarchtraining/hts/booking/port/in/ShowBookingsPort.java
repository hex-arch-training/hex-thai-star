package io.github.hexarchtraining.hts.booking.port.in;

import java.util.List;

public interface ShowBookingsPort {

    List<ShowBookingsResult> showBookings(ShowBookingsQuery showBookingsQuery);
}
