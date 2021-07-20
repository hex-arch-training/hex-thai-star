package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TableBookingRepository extends CrudRepository<TableBookingEntity, Long> {

    int deleteByBookingIdAndTableId(long bookingId, long tableId);

    Optional<TableBookingEntity> findFirstByBookingId(long bookingId);

    @Query("from TableBookingEntity where bookingFrom > :from or bookingTo < :to")
    List<TableBookingEntity> findBookingsIntersect(Instant from, Instant to);
}
