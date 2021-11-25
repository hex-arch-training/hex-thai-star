package io.github.hexarchtraining.hts.booking.adapter.out.jpa;

import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.BookingEntity;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableBookingEntity;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.entity.TableEntity;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.repository.BookingRepository;
import io.github.hexarchtraining.hts.booking.adapter.out.jpa.testdata.BookingJpaTestData;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FindBookingsJpaAdapterTest {

  @Resource
  private BookingRepository bookingRepository;

  private FindBookingsJpaAdapter findBookingsJpaAdapter;

  @BeforeEach
  public void beforeEach() {
    findBookingsJpaAdapter = new FindBookingsJpaAdapter(bookingRepository);
  }

  @Test
  void shouldFindBookings() {
    // given
    bookingRepository.save(BookingJpaTestData.newBooking());

    BookingEntity bookingEntity = bookingRepository.findAll().iterator().next();
    TableBookingEntity tableBookingEntity = bookingEntity.getTableBookings().stream().findFirst().get();
    TableEntity tableEntity = tableBookingEntity.getTable();

    // when
    List<Booking> bookings = findBookingsJpaAdapter.findBookings();

    // then
    // assert Booking
    assertThat(bookings).hasSize(1);
    Booking booking = bookings.stream().findFirst().get();
    assertThat(booking.getCreationDate()).isEqualTo(bookingEntity.getCreationDate());
    assertThat(booking.getBookingFromTime()).isEqualTo(bookingEntity.getBookingFromTime());
    assertThat(booking.getBookingToTime()).isEqualTo(bookingEntity.getBookingToTime());
    assertThat(booking.getBookingDate()).isEqualTo(bookingEntity.getBookingDate());
    assertThat(booking.getExpirationDate()).isEqualTo(bookingEntity.getExpirationDate());
    assertThat(booking.getEmail()).isEqualTo(bookingEntity.getEmail());
    assertThat(booking.getSeatsNumber()).isEqualTo(bookingEntity.getSeatsNumber());
    assertThat(booking.getStatus()).isEqualTo(bookingEntity.getStatus());
    assertThat(booking.getToken()).isEqualTo(bookingEntity.getToken());

    // assert TableBooking
    assertThat(booking.getTableBookings()).hasSize(1);
    TableBooking tableBooking = booking.getTableBookings().stream().findFirst().get();
    assertThat(tableBooking.getSeatsNumber()).isEqualTo(tableBookingEntity.getSeatsNumber());
    assertThat(tableBooking.getTableId().getValue()).isEqualTo(tableEntity.getId());
  }

  @Test
  void shouldFindNoBookings() {
    // given
    // no stored data

    // when
    List<Booking> bookings = findBookingsJpaAdapter.findBookings();

    //then
    assertThat(bookings).isEmpty();
  }
}