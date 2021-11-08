package io.github.hexarchtraining.hts.booking.usecase;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.port.out.BookingStatusEvent;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingStatusEventPort;
import lombok.AllArgsConstructor;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@AllArgsConstructor
public class SendBookingStatusUseCase {

    private static final String END_OF_LINE = "\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER_ENGLISH_LOCALE = DateTimeFormatter
        .ofPattern("d-MMM-yyyy H:mm")
        .withZone(ZoneId.of("UTC"))
        .withLocale(Locale.ENGLISH);

    private final SendBookingStatusEventPort sendBookingStatusEventPort;

    public void sendBookingStatus(Booking booking) {
        sendBookingStatusEventPort.send(
            BookingStatusEvent.from(
                booking.getEmail(),
                buildMailSubject(booking),
                buildMailText(booking)));
    }

    private String buildMailSubject(Booking booking) {
        switch (booking.getStatus()) {
            case NEW:
                return "Booking created";
            case CONFIRMED:
                return "Booking confirmed";
            case CANCELLED:
                return "Booking cancelled";
            case REALIZED:
                return "Booking realized";
            default:
                throw new IllegalArgumentException();
        }
    }

    private String buildMailText(Booking booking) {
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("MY HEX THAI STAR").append(END_OF_LINE);
        mailContent.append("Hi ").append(booking.getEmail()).append(END_OF_LINE);
        mailContent.append("Your booking has been confirmed.").append(END_OF_LINE);
        mailContent.append("Host: ").append(booking.getEmail()).append(END_OF_LINE);
        mailContent.append("Seats number: ").append(booking.getSeatsNumber()).append(END_OF_LINE);
        mailContent.append("Booking id: ").append(booking.getId().getValue()).append(END_OF_LINE);
        mailContent.append("Booking date: ").append(DATE_TIME_FORMATTER_ENGLISH_LOCALE.format(booking.getBookingDate())).append(END_OF_LINE);
        mailContent.append("Booking time: ")
            .append(DATE_TIME_FORMATTER_ENGLISH_LOCALE.format(booking.getBookingFromTime()))
            .append(" - ")
            .append(DATE_TIME_FORMATTER_ENGLISH_LOCALE.format(booking.getBookingToTime()))
            .append(END_OF_LINE);
        return mailContent.toString();
    }
}
