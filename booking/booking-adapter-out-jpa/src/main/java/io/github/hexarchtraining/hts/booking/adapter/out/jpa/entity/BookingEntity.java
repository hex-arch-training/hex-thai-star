package io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity;

import io.github.hexarchtraining.hts.booking.domain.BookingStatus;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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
