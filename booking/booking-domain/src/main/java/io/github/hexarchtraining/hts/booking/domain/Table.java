package io.github.hexarchtraining.hts.booking.domain;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public class Table {
    @Getter @NonNull private final TableId id;

    @Getter private final int maxSeats;
}
