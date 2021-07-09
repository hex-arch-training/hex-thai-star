package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<BookingEntity, Long> {
}
