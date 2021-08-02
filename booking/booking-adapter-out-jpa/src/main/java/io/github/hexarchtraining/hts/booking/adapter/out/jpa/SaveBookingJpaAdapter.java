package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.BookingEntity;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableBookingEntity;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableEntity;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.BookingRepository;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableBookingRepository;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableRepository;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.TableId;
import io.github.hexarchtraining.hts.booking.domain.exception.BookingNotFoundException;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SaveBookingJpaAdapter implements SaveBookingPort {

    private final BookingRepository bookingRepository;

    private final TableBookingRepository tableBookingRepository;

    private final TableRepository tableRepository;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    private final TableBookingMapper tableBookingMapper = TableBookingMapper.INSTANCE;

    @Override
    public void save(Booking booking) {
        bookingRepository.findById(booking.getId().getValue()).ifPresentOrElse(
                bookingEntity -> {
                    bookingMapper.toEntity(booking, bookingEntity);
                    bookingRepository.save(bookingEntity);
                    syncTableBookings(booking, bookingEntity);
                },
                () -> {
                    throw new BookingNotFoundException(booking.getId());
                });
    }

    private void syncTableBookings(Booking booking, BookingEntity bookingEntity) {
        final Set<TableBookingEntity> tableBookingEntities = tableBookingRepository.findAllByBookingId(bookingEntity.getId());

        // update or delete
        tableBookingEntities.forEach(tableBookingEntity -> booking.findTable(new TableId(tableBookingEntity.getTable().getId()))
                .ifPresentOrElse(tableBooking -> {
                    tableBookingMapper.toEntity(tableBooking, tableBookingEntity);
                    mapFromBooking(booking, tableBookingEntity);
                    tableBookingRepository.save(tableBookingEntity);
                }, () -> tableBookingRepository.delete(tableBookingEntity)));

        // insert new
        final Set<TableId> existingBookingEntities = tableBookingEntities.stream()
                .map(TableBookingEntity::getTable)
                .map(TableEntity::getId)
                .map(TableId::new)
                .collect(Collectors.toSet());

        final Set<TableId> newBookings = booking.bookingsAsTableIdStream()
                .filter(o -> !existingBookingEntities.contains(o))
                .collect(Collectors.toSet());

        newBookings.forEach(tableId -> booking.findTable(tableId).ifPresent(tableBooking -> {
            final TableBookingEntity entity = new TableBookingEntity();
            tableBookingMapper.toEntity(tableBooking, entity);
            mapFromBooking(booking, entity);
            entity.setBooking(bookingEntity);
            entity.setTable(tableRepository.getById(tableId.getValue()));
            tableBookingRepository.save(entity);
        }));
    }

    private void mapFromBooking(Booking booking, TableBookingEntity entity) {
        entity.setBookingFrom(booking.getBookingFromTime());
        entity.setBookingTo(booking.getBookingToTime());
    }
}
