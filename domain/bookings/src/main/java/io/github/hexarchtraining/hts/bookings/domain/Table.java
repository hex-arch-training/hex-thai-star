package io.github.hexarchtraining.hts.bookings.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Table {
    @Getter @NonNull private final TableId id;

    @Getter private final int maxSeats;
}
