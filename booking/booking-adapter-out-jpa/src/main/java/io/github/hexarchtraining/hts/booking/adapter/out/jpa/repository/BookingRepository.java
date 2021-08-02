package io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.BookingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepository<BookingEntity, Long> {

    Optional<BookingEntity> findBookingEntityByToken(String token);

    @Query("from BookingEntity b left outer join fetch b.tableBookings")
    List<BookingEntity> findBookingsWithTables();
}
