package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TableBookingRepository extends CrudRepository<TableBookingEntity, Long> {

    int deleteByBookingIdAndTableId(long bookingId, long tableId);

    Optional<TableBookingEntity> findFirstByBookingId(long bookingId);
}
