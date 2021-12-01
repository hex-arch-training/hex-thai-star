package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.enums.EnumStrategy;
import org.jdbi.v3.core.enums.Enums;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jdbi.v3.testing.junit5.JdbiExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(JdbiH2Extension.class)
class FindBookingsJdbiAdapterTest {

  @RegisterExtension
  public JdbiExtension h2Extension = JdbiExtension.h2().
    withPlugin(new SqlObjectPlugin());

  private FindBookingsJdbiAdapter findBookingsJdbiAdapter;

  @BeforeEach
  public void beforeEach() {
    Jdbi jdbi = h2Extension.getJdbi();
    jdbi.configure(Enums.class,
      enums -> enums.setEnumStrategy(EnumStrategy.BY_ORDINAL));
    findBookingsJdbiAdapter = new FindBookingsJdbiAdapter(jdbi);

    //h2Extension.getSharedHandle().execute()
  }

  @Test
  void shouldFindNoBookings() {
    // given
//    FindBookingsJdbiAdapter findBookingsJdbiAdapter = new FindBookingsJdbiAdapter(jdbi);
    // no stored data

    // when
    List<Booking> bookings = findBookingsJdbiAdapter.findBookings();

    //then
    assertThat(bookings).isEmpty();
  }

}