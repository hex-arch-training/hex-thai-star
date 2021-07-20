package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableBookingRepository;
import io.github.hexarchtraining.hts.booking.port.out.BookingWithTable;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class FindBookinsJpaAdapter implements FindBookingsPort {

    private final TableBookingRepository tableBookingRepository;

    private final TableBookingMapper tableBookingMapper = TableBookingMapper.INSTANCE;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    @Override
    public List<BookingWithTable> findBookings() {
        return Streamable.of(tableBookingRepository.findAllWithBookings())
                .map(entity -> new BookingWithTable(
                        bookingMapper.toDomain(entity.getBooking()),
                        Optional.ofNullable(entity).map(tableBookingMapper::toDomain)))
                .toList();
    }
}
