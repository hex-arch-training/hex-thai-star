package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.BookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableBookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.BookingRecord;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableBookingRecord;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableRecord;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.testdata.BookingJdbiTestData;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.common.adapter.out.jdbi.dao.HibernateSequenceDao;
import org.jdbi.v3.core.enums.EnumStrategy;
import org.jdbi.v3.core.enums.Enums;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jdbi.v3.testing.junit5.JdbiExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FindBookingsJdbiAdapterTest {

    @RegisterExtension
    public JdbiExtension h2Extension = JdbiExtension.h2()
        .withPlugin(new SqlObjectPlugin())
        .withConfig(Enums.class, enums -> enums.setEnumStrategy(EnumStrategy.BY_ORDINAL));

    private FindBookingsJdbiAdapter findBookingsJdbiAdapter;

    private HibernateSequenceDao hibernateSequenceDao;
    private BookingDao bookingDao;
    private TableBookingDao tableBookingDao;
    private TableDao tableDao;

    @BeforeEach
    public void beforeEach() {
        findBookingsJdbiAdapter = new FindBookingsJdbiAdapter(h2Extension.getJdbi());

        hibernateSequenceDao = h2Extension.getSharedHandle().attach(HibernateSequenceDao.class);
        bookingDao = h2Extension.getSharedHandle().attach(BookingDao.class);
        tableBookingDao = h2Extension.getSharedHandle().attach(TableBookingDao.class);
        tableDao = h2Extension.getSharedHandle().attach(TableDao.class);

        hibernateSequenceDao.createSequence();
        bookingDao.createTable();
        tableBookingDao.createTable();
        tableDao.createTable();
    }

    @Test
    void shouldFindBookings() {
        // given
        BookingRecord bookingRecord = BookingJdbiTestData.newBooking();
        long bookingId = bookingDao.insertBooking(bookingRecord);

        TableRecord tableRecord = BookingJdbiTestData.newTable();
        long tableId = tableDao.insert(tableRecord);

        TableBookingRecord tableBookingRecord = BookingJdbiTestData.newTableBooking(bookingId, tableId);
        tableBookingDao.insertTableBooking(tableBookingRecord);

        // when
        List<Booking> bookings = findBookingsJdbiAdapter.findBookings();

        // then
        // assert Booking
        assertThat(bookings).hasSize(1);
        Booking booking = bookings.stream().findFirst().get();
        assertThat(booking.getCreationDate()).isEqualTo(bookingRecord.getCreationDate());
        assertThat(booking.getBookingFromTime()).isEqualTo(bookingRecord.getBookingFromTime());
        assertThat(booking.getBookingToTime()).isEqualTo(bookingRecord.getBookingToTime());
        assertThat(booking.getBookingDate()).isEqualTo(bookingRecord.getBookingDate());
        assertThat(booking.getExpirationDate()).isEqualTo(bookingRecord.getExpirationDate());
        assertThat(booking.getEmail()).isEqualTo(bookingRecord.getEmail());
        assertThat(booking.getSeatsNumber()).isEqualTo(bookingRecord.getSeatsNumber());
        assertThat(booking.getStatus()).isEqualTo(bookingRecord.getStatus());
        assertThat(booking.getToken()).isEqualTo(bookingRecord.getToken());

        // assert TableBooking
        assertThat(booking.getTableBookings()).hasSize(1);
        TableBooking tableBooking = booking.getTableBookings().stream().findFirst().get();
        assertThat(tableBooking.getSeatsNumber()).isEqualTo(tableBookingRecord.getSeatsNumber());
        assertThat(tableBooking.getTableId().getValue()).isEqualTo(tableId);
    }

    @Test
    void shouldFindNoBookings() {
        // given
        // no stored data

        // when
        List<Booking> bookings = findBookingsJdbiAdapter.findBookings();

        //then
        assertThat(bookings).isEmpty();
    }

}