package io.github.hexarchtraining.hts.booking.adapter.out.quarkusmail;

import io.github.hexarchtraining.hts.booking.port.out.BookingStatusEvent;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private Mailer mailer;

    @Captor
    private ArgumentCaptor<Mail> mailArgumentCaptor;

    @Test
    void shouldSend() {
        // given
        BookingStatusEvent bookingConfimationEvent = BookingStatusEvent.from(to, subject, text);

        // when
        sendBookingConfirmationAdapter.send(bookingConfimationEvent);

        // then
        verify(mailer).send(mailArgumentCaptor.capture());
        assertThat(mailArgumentCaptor.getValue().getTo()).contains(to);
        assertThat(mailArgumentCaptor.getValue().getSubject()).isEqualTo(subject);
        assertThat(mailArgumentCaptor.getValue().getText()).isEqualTo(text);
    }
}