package io.github.hexarchtraining.hts.order.port.out.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orderxxx")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long bookingId;

    private Long invitedGuestId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderLineEntity> orderLines = new ArrayList<>();
}
