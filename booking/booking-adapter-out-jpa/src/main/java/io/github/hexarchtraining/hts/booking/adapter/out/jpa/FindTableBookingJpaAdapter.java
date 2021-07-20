package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableBookingRepository;
import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.port.out.FindTableBookingPort;
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
