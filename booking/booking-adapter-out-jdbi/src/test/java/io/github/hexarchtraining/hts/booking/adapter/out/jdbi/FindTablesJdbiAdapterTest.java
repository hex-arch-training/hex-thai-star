package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.BookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableBookingDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableRecord;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.testdata.BookingJdbiTestData;
import io.github.hexarchtraining.hts.booking.domain.Table;
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

class FindTablesJdbiAdapterTest {

    @RegisterExtension
    public JdbiExtension h2Extension = JdbiExtension.h2()
        .withPlugin(new SqlObjectPlugin())
        .withConfig(Enums.class, enums -> enums.setEnumStrategy(EnumStrategy.BY_ORDINAL));

    private FindTablesJdbiAdapter findBookingsJdbiAdapter;

    private HibernateSequenceDao hibernateSequenceDao;
    private BookingDao bookingDao;
    private TableBookingDao tableBookingDao;
    private TableDao tableDao;

    @BeforeEach
    public void beforeEach() {
        findBookingsJdbiAdapter = new FindTablesJdbiAdapter(h2Extension.getJdbi());

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
    void shouldFindTables() {
        // given
        TableRecord tableRecord = BookingJdbiTestData.newTable();
        long tableId = tableDao.insert(tableRecord);

        // when
        List<Table> tables = findBookingsJdbiAdapter.findTables();

        // then
        assertThat(tables).hasSize(1);
        Table table = tables.stream().findFirst().get();

        assertThat(table.getMaxSeats()).isEqualTo(tableRecord.getMaxSeats());
        assertThat(table.getId().getValue()).isEqualTo(tableId);
    }

    @Test
    void shouldFindNoTables() {
        // given
        // no stored data

        // when
        List<Table> tables = findBookingsJdbiAdapter.findTables();

        //then
        assertThat(tables).isEmpty();
    }

}