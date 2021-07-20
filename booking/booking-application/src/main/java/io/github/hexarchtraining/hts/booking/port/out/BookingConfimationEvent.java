package io.github.hexarchtraining.hts.booking.port.out;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import lombok.Value;

import java.time.Instant;

@Value
public class BookingConfimationEvent {

    BookingId bookingId;

    String email;

    String token;

    Instant bookingDate;

    Instant bookingFromTime;

    Instant bookingToTime;

    int seatsNumber;

    public static BookingConfimationEvent fromBooking(Booking booking, TableBooking tableBooking) {
        return new BookingConfimationEvent(
                booking.getId(),
                booking.getEmail(),
                booking.getToken(),
                booking.getBookingDate(),
                tableBooking.getBookingFrom(),
                tableBooking.getBookingTo(),
                booking.getSeatsNumber());
    }
}
