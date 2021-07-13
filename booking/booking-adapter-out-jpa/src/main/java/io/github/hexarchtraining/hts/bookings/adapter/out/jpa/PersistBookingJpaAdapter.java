package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

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
