package io.github.hexarchtraining.hts.booking.port.out;

import lombok.Value;

@Value
public class BookingStatusEvent {

    String to;

    String subject;

    String text;

    public static BookingStatusEvent from(String to, String subject, String text) {
        return new BookingStatusEvent(to, subject, text);
    }
}
