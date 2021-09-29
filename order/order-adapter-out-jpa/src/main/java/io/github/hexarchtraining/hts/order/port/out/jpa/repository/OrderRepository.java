package io.github.hexarchtraining.hts.order.port.out.jpa.repository;

import io.github.hexarchtraining.hts.order.port.out.jpa.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

}
