package io.github.hexarchtraining.hts.domain.bookings;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class TableId {
    @Getter private final long value;
}
