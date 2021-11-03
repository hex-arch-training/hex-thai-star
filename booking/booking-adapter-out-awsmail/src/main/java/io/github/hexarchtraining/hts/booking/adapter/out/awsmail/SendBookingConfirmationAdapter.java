package io.github.hexarchtraining.hts.booking.adapter.out.awsmail;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import io.github.hexarchtraining.hts.booking.port.out.BookingConfimationEvent;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingConfirmationPort;
import lombok.RequiredArgsConstructor;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RequiredArgsConstructor
public class SendBookingConfirmationAdapter implements SendBookingConfirmationPort {

    private static final String END_OF_LINE = "\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER_ENGLISH_LOCALE = DateTimeFormatter
        .ofPattern("d-MMM-yyyy H:mm")
        .withZone(ZoneId.of("UTC"))
        .withLocale(Locale.ENGLISH);

    @Override
    public void send(BookingConfimationEvent event) {
        SendEmailRequest request = buildSendEmailRequest(event);

        AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();

        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_WEST_2)
            .build();

        client.sendEmail(request);
    }

    private SendEmailRequest buildSendEmailRequest(BookingConfimationEvent event) {
        Destination destination = new Destination().withToAddresses(event.getEmail());
        Content subject = new Content().withData("Booking confirmation");
        Content textBody = new Content().withData(buildMailContent(event));
        Body body = new Body().withText(textBody);
        Message message = new Message().withSubject(subject).withBody(body);
        SendEmailRequest request = new SendEmailRequest().withDestination(destination).withMessage(message);
        return request;
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
