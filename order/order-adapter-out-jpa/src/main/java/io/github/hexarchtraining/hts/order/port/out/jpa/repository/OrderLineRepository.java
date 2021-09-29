package io.github.hexarchtraining.hts.order.port.out.jpa.repository;

import io.github.hexarchtraining.hts.order.port.out.jpa.entity.OrderLineEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderLineRepository extends CrudRepository<OrderLineEntity, Long> {

}
