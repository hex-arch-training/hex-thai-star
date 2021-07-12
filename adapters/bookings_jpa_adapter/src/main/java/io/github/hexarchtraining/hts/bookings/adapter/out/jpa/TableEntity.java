package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class TableEntity {

    @Id
    private Long id;

    private int maxSeats;
}
