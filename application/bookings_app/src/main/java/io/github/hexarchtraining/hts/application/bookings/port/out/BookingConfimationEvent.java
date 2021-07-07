package io.github.hexarchtraining.hts.application.bookings.port.out;

import io.github.hexarchtraining.hts.domain.bookings.Booking;
import io.github.hexarchtraining.hts.domain.bookings.TableBooking;
import lombok.Value;

import java.time.Instant;

@Value
public class BookingConfimationEvent {

    String email;

    String token;

    Instant bookingDate;

    Instant bookingFromTime;

    Instant bookingToTime;

    int seatsNumber;

    public static BookingConfimationEvent fromBooking(Booking booking, TableBooking tableBooking) {
        return new BookingConfimationEvent(
                booking.getEmail(),
                booking.getToken(),
                booking.getBookingDate(),
                tableBooking.getBookingFrom(),
                tableBooking.getBookingTo(),
                booking.getSeatsNumber());
    }
}
