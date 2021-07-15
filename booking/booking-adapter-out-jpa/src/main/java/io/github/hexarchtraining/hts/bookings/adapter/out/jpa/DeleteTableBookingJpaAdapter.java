package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import io.github.hexarchtraining.hts.bookings.domain.TableBooking;
import io.github.hexarchtraining.hts.bookings.port.out.DeleteTableBookingPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTableBookingJpaAdapter implements DeleteTableBookingPort {

    private final TableBookingRepository tableBookingRepository;

    @Override
    public void delete(TableBooking tableBooking) {
        tableBookingRepository.deleteByBookingIdAndTableId(tableBooking.getBookingId().getValue(), tableBooking.getTableId().getValue());
    }
}
