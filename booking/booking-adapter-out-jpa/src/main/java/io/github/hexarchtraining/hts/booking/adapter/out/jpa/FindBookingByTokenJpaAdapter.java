package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.BookingRepository;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.port.in.bci.BookingByTokenResult;
import io.github.hexarchtraining.hts.booking.port.in.bci.FindBookingByTokenCommand;
import io.github.hexarchtraining.hts.booking.port.in.bci.FindBookingByTokenUseCase;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingByTokenPort;
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
