package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import io.github.hexarchtraining.hts.bookings.domain.Booking;
import io.github.hexarchtraining.hts.bookings.port.out.FindBookingByTokenPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class FindBookingByTokenJpaAdapter implements FindBookingByTokenPort {

    private final BookingRepository bookingRepository;

    private final BookingMapper mapper = BookingMapper.INSTANCE;

    @Override
    public Optional<Booking> find(String token) {
        return bookingRepository.findBookingEntityByToken(token).map(mapper::toDomain);
    }
}
