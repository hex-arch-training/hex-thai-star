package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class TableEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "max_seats")
    private int maxSeats;
}
