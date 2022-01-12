package io.github.hexarchtraining.hts.order.port.out.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "order_line")
public class OrderLineEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long dishId;

    private Integer amount;

    private String comment;
}
