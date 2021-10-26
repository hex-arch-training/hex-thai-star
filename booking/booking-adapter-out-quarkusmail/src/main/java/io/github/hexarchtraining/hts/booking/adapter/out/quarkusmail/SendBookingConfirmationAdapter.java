package io.github.hexarchtraining.hts.booking.adapter.out.quarkusmail;

import io.github.hexarchtraining.hts.booking.port.out.BookingConfimationEvent;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingConfirmationPort;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@ApplicationScoped
@RequiredArgsConstructor
public class SendBookingConfirmationAdapter implements SendBookingConfirmationPort {

    private static final String END_OF_LINE = "\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER_ENGLISH_LOCALE = DateTimeFormatter
        .ofPattern("d-MMM-yyyy H:mm")
        .withZone(ZoneId.of("UTC"))
        .withLocale(Locale.ENGLISH);

    private final Mailer mailer;

    @Override
    public void send(BookingConfimationEvent event) {
        mailer.send(
            Mail.withText(event.getEmail(),
                "Booking confirmation",
                buildMailContent(event)
            )
        );
    }

    private String buildMailContent(BookingConfimationEvent event) {
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("MY HEX THAI STAR").append(END_OF_LINE);
        mailContent.append("Hi ").append(event.getEmail()).append(END_OF_LINE);
        mailContent.append("Your booking has been confirmed.").append(END_OF_LINE);
        mailContent.append("Host: ").append(event.getEmail()).append(END_OF_LINE);
        mailContent.append("Seats number: ").append(event.getSeatsNumber()).append(END_OF_LINE);
        mailContent.append("Booking id: ").append(event.getBookingId().getValue()).append(END_OF_LINE);
        mailContent.append("Booking date: ").append(DATE_TIME_FORMATTER_ENGLISH_LOCALE.format(event.getBookingDate())).append(END_OF_LINE);
        mailContent.append("Booking time: ")
            .append(DATE_TIME_FORMATTER_ENGLISH_LOCALE.format(event.getBookingFromTime()))
            .append(" - ")
            .append(DATE_TIME_FORMATTER_ENGLISH_LOCALE.format(event.getBookingToTime()))
            .append(END_OF_LINE);
        return mailContent.toString();
    }
}
