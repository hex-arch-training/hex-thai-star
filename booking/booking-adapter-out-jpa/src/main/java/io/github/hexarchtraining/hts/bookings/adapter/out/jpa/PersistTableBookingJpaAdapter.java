package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import io.github.hexarchtraining.hts.bookings.domain.BusinessException;
import io.github.hexarchtraining.hts.bookings.domain.TableBooking;
import io.github.hexarchtraining.hts.bookings.port.out.PersistTableBookingPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersistTableBookingJpaAdapter implements PersistTableBookingPort {

    private final TableBookingRepository tableBookingRepository;

    private final TableRepository tableRepository;

    private final BookingRepository bookingRepository;

    private final TableBookingMapper tableBookingMapper = TableBookingMapper.INSTANCE;

    @Override
    public void persist(TableBooking tableBooking) {
        final TableBookingEntity entity = new TableBookingEntity();
        tableBookingMapper.toEntity(tableBooking, entity);
        entity.setBooking(bookingRepository.findById(tableBooking.getBookingId().getValue())
                .orElseThrow(() -> new BusinessException("Booking not found")));
        entity.setTable(tableRepository.findById(tableBooking.getTableId().getValue())
                .orElseThrow(() -> new BusinessException("Table not found")));
        tableBookingRepository.save(entity);
    }
}
