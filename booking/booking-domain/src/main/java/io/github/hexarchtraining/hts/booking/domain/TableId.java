package io.github.hexarchtraining.hts.booking.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class TableId {
    @Getter private final long value;
}
