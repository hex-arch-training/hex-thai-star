package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import io.github.hexarchtraining.hts.bookings.domain.BookingId;
import io.github.hexarchtraining.hts.bookings.domain.TableBooking;
import io.github.hexarchtraining.hts.bookings.port.out.FindTableBookingPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class FindTableBookingJpaAdapter implements FindTableBookingPort {

    private final TableBookingRepository tableBookingRepository;

    private final TableBookingMapper tableBookingMapper = TableBookingMapper.INSTANCE;

    @Override
    public Optional<TableBooking> find(BookingId id) {
        return tableBookingRepository.findFirstByBookingId(id.getValue()).map(tableBookingMapper::toDomain);
    }
}
