package io.github.hexarchtraining.hts.order.port.out;

public interface FindBookingForOrderByTokenPort {

    FindBookingByTokenResult findBookingByToken(String token);
}
