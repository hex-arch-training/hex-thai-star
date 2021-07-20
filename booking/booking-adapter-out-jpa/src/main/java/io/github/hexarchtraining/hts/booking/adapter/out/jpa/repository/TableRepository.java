package io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableEntity;
import org.springframework.data.repository.CrudRepository;

public interface TableRepository extends CrudRepository<TableEntity, Long> {

}
