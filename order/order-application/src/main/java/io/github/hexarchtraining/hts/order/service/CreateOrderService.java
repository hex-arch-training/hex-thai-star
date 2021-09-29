package io.github.hexarchtraining.hts.order.service;

import io.github.hexarchtraining.hts.common.domain.token.TokenType;
import io.github.hexarchtraining.hts.common.domain.token.exception.InvalidTokenException;
import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import io.github.hexarchtraining.hts.order.domain.Order;
import io.github.hexarchtraining.hts.order.domain.exception.NoBookingException;
import io.github.hexarchtraining.hts.order.domain.exception.NoInviteException;
import io.github.hexarchtraining.hts.order.domain.exception.OrderAlreadyExistException;
import io.github.hexarchtraining.hts.order.port.in.*;
import io.github.hexarchtraining.hts.order.port.out.*;
import io.github.hexarchtraining.hts.order.domain.OrderLine;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    private final FindBookingForOrderByTokenPort findBookingForOrderByTokenPort;
    private final TransactionPort transactionPort;
    private final CreateOrderPort createOrderPort;
    private final SendOrderConfirmationPort sendOrderConfirmationPort;

    @Override
    public CreateOrderResult createOrder(CreateOrderCommand command) {
        return transactionPort.inTransaction(() -> {
            Order newOrder = validateCreateCommand(command);
            List<OrderLine> orderLines = command.getOrderLines().stream().map(line -> OrderLine.createNewOrderLine(line.getDishId(), line.getAmount(), line.getComment())).collect(Collectors.toUnmodifiableList());
            newOrder.addOrderLines(orderLines);
            Order savedOrder = createOrderPort.create(newOrder);
            sendOrderConfirmationPort.sendOrderConfirmation(OrderConfirmationEvent.fromOrder(savedOrder));
            return new CreateOrderResult(savedOrder.getId().getValue());
        });
    }

    private Order validateCreateCommand(CreateOrderCommand command) {
//        // BOOKING VALIDATION
        String token = command.getToken();
        TokenType tokenType = command.getTokenType();
        if(tokenType == TokenType.COMMON) {
            FindBookingByTokenResult booking = getBookingByToken(token);
            if (booking == null) {
                throw new NoBookingException("No booking found for given token");
            }

            if (hasBookingOrder(booking.getBookingId())) {
                throw new OrderAlreadyExistException("Booking already has Orders");
            }

            return Order.createNewOrderForBooking(booking.getBookingId());
        } else if (tokenType == TokenType.INVITED_GUEST) {

            InvitedGuestResult invitedGuest = getInvitedGuestByToken(token);
            if (invitedGuest == null) {
                throw new NoInviteException("No Guest found for token");
            }
            if(hasGuestOrder(invitedGuest.getGuestId())) {
                throw new OrderAlreadyExistException("Invited Guest already has Orders");
            }
            return Order.createNewOrderForIvitedGuest(invitedGuest.getBookingId(), invitedGuest.getGuestId());

        } else {
            throw new InvalidTokenException("Unsupported token type");
        }
    }

    private boolean hasGuestOrder(Long guestId) {
        return false;
    }

    private InvitedGuestResult getInvitedGuestByToken(String token) {
        return null;
    }

    private boolean hasBookingOrder(Long bookingId) {
        return false;
    }

    private FindBookingByTokenResult getBookingByToken(String bookingToken) {
        return findBookingForOrderByTokenPort.findBookingByToken(bookingToken);
    }
}
