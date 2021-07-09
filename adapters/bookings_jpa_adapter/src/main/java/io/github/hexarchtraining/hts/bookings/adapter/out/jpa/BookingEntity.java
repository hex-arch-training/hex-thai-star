package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import io.github.hexarchtraining.hts.bookings.domain.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter
@Setter
public class BookingEntity {
    @Id
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
