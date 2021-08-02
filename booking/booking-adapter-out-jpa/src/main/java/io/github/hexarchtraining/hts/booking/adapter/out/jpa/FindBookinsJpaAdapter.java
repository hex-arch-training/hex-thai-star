package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.BookingRepository;
import io.github.hexarchtraining.hts.booking.port.out.BookingWithTable;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;

import java.util.List;

@AllArgsConstructor
public class FindBookinsJpaAdapter implements FindBookingsPort {

    private final BookingRepository bookingRepository;

    private final TableBookingMapper tableBookingMapper = TableBookingMapper.INSTANCE;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    @Override
    public List<BookingWithTable> findBookings() {
        return Streamable.of(bookingRepository.findBookingsWithTables())
                .map(entity -> new BookingWithTable(
                        bookingMapper.toDomain(entity),
                        entity.getTableBookingEntities().stream().findAny().map(tableBookingMapper::toDomain)))
                .toList();
    }
}
