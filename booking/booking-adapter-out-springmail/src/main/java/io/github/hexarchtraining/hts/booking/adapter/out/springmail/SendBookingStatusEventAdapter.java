package io.github.hexarchtraining.hts.booking.adapter.out.springmail;

import io.github.hexarchtraining.hts.booking.port.out.BookingStatusEvent;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingStatusEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendBookingStatusEventAdapter implements SendBookingStatusEventPort {

    private final MailSender mailSender;

    @Override
    public void send(BookingStatusEvent event) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getTo());
        message.setSubject(event.getSubject());
        message.setText(event.getText());
        mailSender.send(message);
    }
}
