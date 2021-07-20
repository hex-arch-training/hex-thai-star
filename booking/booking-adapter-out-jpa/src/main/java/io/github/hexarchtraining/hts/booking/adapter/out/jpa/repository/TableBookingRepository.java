package io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableBookingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TableBookingRepository extends CrudRepository<TableBookingEntity, Long> {

    int deleteByBookingIdAndTableId(long bookingId, long tableId);

    Optional<TableBookingEntity> findFirstByBookingId(long bookingId);

    // TODO change compiler settings to use -parameters switch
    @Query("from TableBookingEntity where (bookingFrom <= :fromTime and bookingTo >= :fromTime) or (bookingFrom <= :toTime and bookingTo >= :toTime) or (bookingFrom > :fromTime and bookingTo < :toTime)")
    List<TableBookingEntity> findBookingsIntersect(@Param("fromTime") Instant from, @Param("toTime") Instant to);

    @Query("from TableBookingEntity tb join fetch tb.booking")
    List<TableBookingEntity> findAllWithBookings();
}
