package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.BookingRepository;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;

import java.util.List;

@AllArgsConstructor
public class FindBookinsJpaAdapter implements FindBookingsPort {

    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    @Override
    public List<Booking> findBookings() {
        return Streamable.of(bookingRepository.findBookingsWithTables())
                .map(bookingMapper::toDomain)
                .toList();
    }
}
