package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter
@Setter
public class TableBookingEntity {

    @Id
    private Long id;

    private Long tableId;

    private Long bookingId;

    private Instant bookingFrom;

    private Instant bookingTo;
}
