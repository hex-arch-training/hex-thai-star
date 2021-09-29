package io.github.hexarchtraining.hts.order.port.out;

import lombok.Value;

@Value
public class InvitedGuestResult {
    Long guestId;
    Long bookingId;
}
