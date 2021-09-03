package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.BookingRepository;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FindBookinsJpaAdapter implements FindBookingsPort {

    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    @Override
    public List<Booking> findBookings() {
        return bookingRepository.findBookingsWithTables()
            .stream()
            .map(bookingMapper::toDomain)
            .collect(Collectors.toList());
    }
}
