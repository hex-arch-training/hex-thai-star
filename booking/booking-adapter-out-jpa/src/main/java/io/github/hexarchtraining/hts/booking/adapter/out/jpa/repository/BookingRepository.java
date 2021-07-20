package io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.BookingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookingRepository extends CrudRepository<BookingEntity, Long> {

    Optional<BookingEntity> findBookingEntityByToken(String token);
}
