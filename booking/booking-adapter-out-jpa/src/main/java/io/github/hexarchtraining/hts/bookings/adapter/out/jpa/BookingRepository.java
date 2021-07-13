package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookingRepository extends CrudRepository<BookingEntity, Long> {

    Optional<BookingEntity> findBookingEntityByToken(String token);
}
