package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record;

import io.github.hexarchtraining.hts.booking.domain.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRecord {
    private Long id;
    private Instant creationDate;
    private Instant bookingFromTime;
    private Instant bookingToTime;
    private Instant bookingDate;
    private Instant expirationDate;
    private String email;
    private int seatsNumber;
    private BookingStatus status;
    private String token;
}
