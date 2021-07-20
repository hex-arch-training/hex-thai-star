package io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableBookingEntity;
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
