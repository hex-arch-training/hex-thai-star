package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.TableBookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableBookingRepository;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;

import java.util.List;

@AllArgsConstructor
public class FindBookinsJpaAdapter implements FindBookingsPort {

    private final TableBookingRepository tableBookingRepository;

    private final TableBookingMapper mapper = TableBookingMapper.INSTANCE;

    @Override
    public List<TableBooking> findBookings() {
        return Streamable.of(tableBookingRepository.findAll())
                .map(mapper::toDomain)
                .toList();
    }
}
