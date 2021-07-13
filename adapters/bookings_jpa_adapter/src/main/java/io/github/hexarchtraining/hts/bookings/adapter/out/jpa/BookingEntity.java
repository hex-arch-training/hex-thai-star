package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import io.github.hexarchtraining.hts.bookings.domain.BookingStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Data
@Table(name = "booking")
public class BookingEntity {
    @Id
    @GeneratedValue
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
