package io.github.hexarchtraining.hts.booking.adapter.out.springmail;

import io.github.hexarchtraining.hts.booking.port.out.BookingStatusEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendBookingConfirmationAdapterTest {

    private final String to = "john@example.com";
    private final String subject = "This is a subject";
    private final String text = "This is a text";

    @InjectMocks
    private SendBookingStatusEventAdapter sendBookingConfirmationAdapter;

    @Mock
    private MailSender mailSender;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> simpleMailMessageArgumentCaptor;

    @Test
    void shouldSend() {
        // given
        BookingStatusEvent bookingConfimationEvent = BookingStatusEvent.from(to, subject, text);

        // when
        sendBookingConfirmationAdapter.send(bookingConfimationEvent);

        // then
        verify(mailSender).send(simpleMailMessageArgumentCaptor.capture());
        assertThat(simpleMailMessageArgumentCaptor.getValue().getTo()).contains(to);
        assertThat(simpleMailMessageArgumentCaptor.getValue().getSubject()).isEqualTo(subject);
        assertThat(simpleMailMessageArgumentCaptor.getValue().getText()).isEqualTo(text);
    }

}