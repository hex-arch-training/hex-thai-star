package io.github.hexarchtraining.hts.domain.bookings;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Table {
    @Getter @NonNull private final TableId id;

    @Getter private final int maxSeats;
}
