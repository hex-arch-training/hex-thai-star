package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.BookingEntity;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.BookingRepository;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.BookingMapper;
import io.github.hexarchtraining.hts.bookings.domain.Booking;
import io.github.hexarchtraining.hts.bookings.port.out.PersistBookingPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersistBookingJpaAdapter implements PersistBookingPort {

    private final BookingRepository bookingRepository;

    private final BookingMapper mapper = BookingMapper.INSTANCE;

    public Booking persist(Booking booking) {
        final BookingEntity entity = new BookingEntity();
        mapper.toEntity(booking, entity);
        return mapper.toDomain(bookingRepository.save(entity));
    }
}
