package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.TableBookingRepository;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.port.out.DeleteTableBookingPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTableBookingJpaAdapter implements DeleteTableBookingPort {

    private final TableBookingRepository tableBookingRepository;

    @Override
    public void delete(TableBooking tableBooking) {
        tableBookingRepository.deleteByBookingIdAndTableId(
                tableBooking.getBookingId().getValue(),
                tableBooking.getTableId().getValue());
    }
}
