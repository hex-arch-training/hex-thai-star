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
import io.github.hexarchtraining.hts.booking.port.out.BookingStatusEvent;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingStatusEventPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SendBookingStatusEventAdapter implements SendBookingStatusEventPort {

    @Override
    public void send(BookingStatusEvent event) {
        SendEmailRequest request = buildSendEmailRequest(event);

        AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();

        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.EU_CENTRAL_1)
            .build();

        client.sendEmail(request);
    }

    private SendEmailRequest buildSendEmailRequest(BookingStatusEvent event) {
        Destination destination = new Destination().withToAddresses(event.getTo());
        Content subject = new Content().withData(event.getSubject());
        Content textBody = new Content().withData(event.getText());
        Body body = new Body().withText(textBody);
        Message message = new Message().withSubject(subject).withBody(body);
        SendEmailRequest request = new SendEmailRequest().withDestination(destination).withMessage(message);
        return request;
    }
}
