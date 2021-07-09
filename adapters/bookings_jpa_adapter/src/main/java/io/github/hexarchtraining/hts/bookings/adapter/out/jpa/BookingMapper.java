package io.github.hexarchtraining.hts.bookings.adapter.out.jpa;

import io.github.hexarchtraining.hts.bookings.domain.Booking;
import io.github.hexarchtraining.hts.bookings.domain.BookingId;

class BookingMapper {

    Booking toDomain(BookingEntity bookingEntity) {
        return Booking.hydrate(
                new BookingId(bookingEntity.getId()),
                bookingEntity.getCreationDate(),
                bookingEntity.getBookingDate(),
                bookingEntity.getBookingFromTime(),
                bookingEntity.getBookingToTime(),
                bookingEntity.getExpirationDate(),
                bookingEntity.getEmail(),
                bookingEntity.getSeatsNumber(),
                bookingEntity.getStatus(),
                bookingEntity.getToken());
    }

    void toEntity(Booking booking, BookingEntity bookingEntity) {
        bookingEntity.setCreationDate(booking.getCreationDate());
        bookingEntity.setBookingDate(booking.getBookingDate());
        bookingEntity.setBookingFromTime(booking.getBookingFromTime());
        bookingEntity.setBookingToTime(booking.getBookingToTime());
        bookingEntity.setExpirationDate(booking.getExpirationDate());
        bookingEntity.setEmail(booking.getEmail());
        bookingEntity.setSeatsNumber(booking.getSeatsNumber());
        bookingEntity.setStatus(booking.getStatus());
        bookingEntity.setToken(booking.getToken());
    }
}
