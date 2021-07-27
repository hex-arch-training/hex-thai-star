package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record;

import io.github.hexarchtraining.hts.booking.domain.BookingStatus;
import lombok.Value;

import java.time.Instant;

@Value
public class BookingRecord {
    Long id;
    Instant creationDate;
    Instant bookingFromTime;
    Instant bookingToTime;
    Instant bookingDate;
    Instant expirationDate;
    String email;
    int seatsNumber;
    BookingStatus status;
    String token;
}
