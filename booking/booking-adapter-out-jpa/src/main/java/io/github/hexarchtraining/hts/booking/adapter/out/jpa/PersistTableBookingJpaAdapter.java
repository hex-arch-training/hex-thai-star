package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableBookingEntity;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.BookingRepository;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableBookingRepository;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableRepository;
import io.github.hexarchtraining.hts.booking.domain.BusinessException;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.port.out.PersistTableBookingPort;
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
