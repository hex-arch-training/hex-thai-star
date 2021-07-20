package io.github.hexarchtraining.hts.booking.port.in;

import java.util.List;

public interface ShowBookingsUseCase {

    List<ShowBookingsResult> showBookings(ShowBookingsQuery showBookingsQuery);
}
