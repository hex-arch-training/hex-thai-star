package io.github.hexarchtraining.hts.booking.adapter.out.quarkusmail;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.domain.BookingStatus;
import io.github.hexarchtraining.hts.booking.port.out.BookingStatusEvent;
import io.github.hexarchtraining.hts.booking.port.out.SendBookingStatusEventPort;
import io.github.hexarchtraining.hts.booking.service.SendBookingStatusService;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendBookingStatusServiceTest {

    private final static BookingId BOOKING_ID = new BookingId(6394287366L);
    private final static String EMAIL = "john@example.com";
    private final static String BOOKING_TOKEN = "CB_20210827_18e151ee9dc8c89d3f3c4e705f6b79ee";
    private final static Instant BOOKING_DATE = Instant.parse("2021-08-28T13:00:00Z");
    private final static Instant BOOKING_FROM_TIME = Instant.parse("2021-08-28T17:00:00Z");
    private final static Instant BOOKING_TO_TIME = Instant.parse("2021-08-28T21:00:00Z");
    private final static int SEATS_NUMBER = 2;

    private final static String THEN_TEXT = "" +
        "MY HEX THAI STAR\n" +
        "Hi john@example.com\n" +
        "Your booking has been confirmed.\n" +
        "Host: john@example.com\n" +
        "Seats number: 2\n" +
        "Booking id: 6394287366\n" +
        "Booking date: 28-Aug-2021 13:00\n" +
        "Booking time: 28-Aug-2021 17:00 - 28-Aug-2021 21:00\n";

    @InjectMocks
    private SendBookingStatusService sendBookingStatusService;

    @Mock
    private SendBookingStatusEventPort sendBookingStatusEventPort;

    @Captor
    private ArgumentCaptor<BookingStatusEvent> bookingStatusEventArgumentCaptor;

    @ParameterizedTest(name = "{1}")
    @MethodSource("shouldSendBookingStatusProvider")
    void shouldSendBookingStatus(Booking givenBooking, String thenSubject, String thenText) {
        // given from provider

        // when
        sendBookingStatusService.sendBookingStatus(givenBooking);

        // then
        verify(sendBookingStatusEventPort).send(bookingStatusEventArgumentCaptor.capture());
        assertThat(bookingStatusEventArgumentCaptor.getValue().getTo()).contains(givenBooking.getEmail());
        assertThat(bookingStatusEventArgumentCaptor.getValue().getSubject()).isEqualTo(thenSubject);
        assertThat(bookingStatusEventArgumentCaptor.getValue().getText()).isEqualTo(thenText);
    }

    private static Stream<Arguments> shouldSendBookingStatusProvider() {
        return Stream.of(
            Arguments.of(getBooking(BookingStatus.NEW), "Booking created", THEN_TEXT),
            Arguments.of(getBooking(BookingStatus.CONFIRMED), "Booking confirmed", THEN_TEXT),
            Arguments.of(getBooking(BookingStatus.CANCELLED), "Booking cancelled", THEN_TEXT),
            Arguments.of(getBooking(BookingStatus.REALIZED), "Booking realized", THEN_TEXT)
        );
    }

    private static Booking getBooking(BookingStatus status) {
        Booking booking = Booking.builder()
            .status(status)
            .id(BOOKING_ID)
            .email(EMAIL)
            .token(BOOKING_TOKEN)
            .bookingDate(BOOKING_DATE)
            .bookingFromTime(BOOKING_FROM_TIME)
            .bookingToTime(BOOKING_TO_TIME)
            .seatsNumber(SEATS_NUMBER)
            .tableBookings(Sets.newHashSet())
            .build();
        return booking;
    }

}