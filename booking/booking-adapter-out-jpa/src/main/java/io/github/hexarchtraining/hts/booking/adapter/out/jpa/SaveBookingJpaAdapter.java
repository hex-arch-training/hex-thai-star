package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.mapper.BookingMapper;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.BookingRepository;
import io.github.hexarchtraining.hts.bookings.domain.Booking;
import io.github.hexarchtraining.hts.bookings.domain.BusinessException;
import io.github.hexarchtraining.hts.bookings.port.out.SaveBookingPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveBookingJpaAdapter implements SaveBookingPort {

    private final BookingRepository bookingRepository;

    private final BookingMapper mapper = BookingMapper.INSTANCE;

    @Override
    public void save(Booking booking) {
        bookingRepository.findById(booking.getId().getValue()).ifPresentOrElse(
                bookingEntity -> {
                    mapper.toEntity(booking, bookingEntity);
                    bookingRepository.save(bookingEntity);
                },
                () -> {
                    throw new BusinessException("Booking not found.");
                });
    }
}
