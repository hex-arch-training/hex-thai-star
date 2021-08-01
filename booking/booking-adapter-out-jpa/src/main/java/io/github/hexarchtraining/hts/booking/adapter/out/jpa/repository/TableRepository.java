package io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

}
