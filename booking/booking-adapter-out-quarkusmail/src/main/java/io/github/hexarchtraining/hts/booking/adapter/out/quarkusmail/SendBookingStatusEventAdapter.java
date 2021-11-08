package io.github.hexarchtraining.hts.booking.adapter.out.quarkusmail;

import io.github.hexarchtraining.hts.booking.port.out.BookingStatusEvent;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingStatusEventPort;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class SendBookingStatusEventAdapter implements SendBookingStatusEventPort {

    private final Mailer mailer;

    @Override
    public void send(BookingStatusEvent event) {
        mailer.send(
            Mail.withText(event.getTo(),
                event.getSubject(),
                event.getText())
        );
    }
}

