package io.github.hexarchtraining.hts.booking.adapter.out.quarkusmail;

import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.port.out.BookingConfimationEvent;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendBookingConfirmationAdapterTest {

    private final BookingId bookingId = new BookingId(6394287366L);
    private final String email = "john@example.com";
    private final String bookingToken = "CB_20210827_18e151ee9dc8c89d3f3c4e705f6b79ee";
    private final Instant bookingDate = Instant.parse("2021-08-28T13:00:00Z");
    private final Instant bookingFromTime = Instant.parse("2021-08-28T17:00:00Z");
    private final Instant bookingToTime = Instant.parse("2021-08-28T21:00:00Z");
    private final int seatsNumber = 2;

    @InjectMocks
    private SendBookingConfirmationAdapter sendBookingConfirmationAdapter;

    @Mock
    private Mailer mailer;

    @Captor
    private ArgumentCaptor<Mail> mailArgumentCaptor;

    @Test
    void shouldSendBookingConfirmation() {
        // given
        BookingConfimationEvent bookingConfimationEvent = createValidBookingConfimationEvent();

        // when
        sendBookingConfirmationAdapter.send(bookingConfimationEvent);

        // then
        verify(mailer).send(mailArgumentCaptor.capture());
        assertThat(mailArgumentCaptor.getValue().getTo()).contains(email);
        assertThat(mailArgumentCaptor.getValue().getSubject()).isEqualTo("Booking confirmation");
        assertThat(mailArgumentCaptor.getValue().getText()).isEqualTo("" +
            "MY HEX THAI STAR\n" +
            "Hi john@example.com\n" +
            "Your booking has been confirmed.\n" +
            "Host: john@example.com\n" +
            "Seats number: 2\n" +
            "Booking id: 6394287366\n" +
            "Booking date: 28-Aug-2021 13:00\n" +
            "Booking time: 28-Aug-2021 17:00 - 28-Aug-2021 21:00\n"
        );
    }

    private BookingConfimationEvent createValidBookingConfimationEvent() {
        return new BookingConfimationEvent(
            bookingId,
            email,
            bookingToken,
            bookingDate,
            bookingFromTime,
            bookingToTime,
            seatsNumber
        );
    }
}