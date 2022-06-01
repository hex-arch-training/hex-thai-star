package io.github.hexarchtraining.hts.order.port.in;

import io.github.hexarchtraining.hts.common.domain.token.TokenType;
import io.github.hexarchtraining.hts.common.domain.token.TokenUtils;
import io.github.hexarchtraining.hts.common.domain.token.exception.InvalidTokenException;
import io.github.hexarchtraining.hts.order.domain.exception.OrderValidationException;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class CreateOrderCommand {
    String token;
    List<OrderLine> orderLines;
    TokenType tokenType;

    public CreateOrderCommand(@NonNull String token, @NonNull List<OrderLine> orderLines) {
        this.token = token;
        this.orderLines = orderLines;
        if (!TokenUtils.checkTokenValid(this.token)) {
            throw new InvalidTokenException("Invalid Token ");
        }
        this.tokenType = TokenUtils.getTokenType(token);
        checkOrderLines(this.orderLines);
    }

    private void checkOrderLines(List<OrderLine> orderLines) {
        if (orderLines.isEmpty()) {
            throw new OrderValidationException("Order Line missing!");
        }
    }

}
