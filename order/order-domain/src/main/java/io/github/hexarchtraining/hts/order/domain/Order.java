package io.github.hexarchtraining.hts.order.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Order {
    @Getter private final OrderId id;

    @Getter private final List<OrderLine> orderLines;

    @Getter private final Long bookingId;

    @Getter private final Long invitedGuestId;

    public static Order createNewOrderForBooking(Long bookingId) {
        return new Order(null, new ArrayList<>(), bookingId, null);
    }

    public static Order createNewOrderForIvitedGuest(Long bookingId, Long invitedGuestId) {
        return new Order(null, new ArrayList<>(), bookingId, invitedGuestId);
    }

    private static Order createNewOrder(OrderId id, List<OrderLine> orderLines, Long bookingId, Long invitedGuestId) {
        List<OrderLine> nonNullOrderLine = orderLines == null ? new ArrayList<>() : orderLines;
        return new Order(id, nonNullOrderLine, bookingId, invitedGuestId);
    }

    public void addOrderLines(List<OrderLine> orderLines) {
        this.orderLines.addAll(orderLines);
    }

}
