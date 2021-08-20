package io.github.hexarchtraining.hts.booking.adapter.out.dynamodb;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.domain.TableId;
import lombok.Getter;
import lombok.NonNull;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;
import java.util.stream.Collectors;

public class TableAndBookingMapper {
    private static final String TABLE_ID_PREFIX = "T#";
    private static final String BOOKING_ID_PREFIX = "B#";

    private static final String PK_ATTR_NAME = "PK";
    private static final String SK_ATTR_NAME = "SK";
    private static final String TABLE_MAX_SEATS_ATTR_NAME = "maxSeats";

    @Getter
    private final List<Table> tables = new ArrayList<>();
    @Getter
    private final List<Booking> bookings = Collections.emptyList();;


    public TableAndBookingMapper from(@NonNull List<Map<String, AttributeValue>> tableAndBookingAttrs) {
        Map<String, List<Map<String, AttributeValue>>> tablesAndBookingsByTheirIds = tableAndBookingAttrs.
                stream().collect(Collectors.groupingBy(singleTableAndBookingAttrs -> {
            if (!singleTableAndBookingAttrs.containsKey(PK_ATTR_NAME)) {
                throw new IllegalArgumentException("PK attribute is missing!");
            }
            if (!singleTableAndBookingAttrs.containsKey(SK_ATTR_NAME)) {
                throw new IllegalArgumentException("SK attribute is missing!");
            }
            return singleTableAndBookingAttrs.get(PK_ATTR_NAME).s();
        }));

        tablesAndBookingsByTheirIds.forEach((primaryKey, tableAttrs) -> {
            if (primaryKey.startsWith(TABLE_ID_PREFIX)) {
                if (tableAttrs.size() != 1) {
                    throw new IllegalStateException("PK is unique for tables");
                }
                tables.add(createTable(tableAttrs.get(0)));
            } else if (primaryKey.startsWith(BOOKING_ID_PREFIX)) {
                throw new UnsupportedOperationException();
            } else {
                throw new IllegalStateException("Unknown PK prefix :".concat(primaryKey));
            }
        });

        return this;
    }

    private Table createTable(@NonNull Map<String, AttributeValue> values) {
        final Table.TableBuilder tableBuilder = Table.builder();

        final String primaryKey = values.get(PK_ATTR_NAME).s();
        final String tableIdAsString = primaryKey.substring(TABLE_ID_PREFIX.length());
        final TableId tableId = new TableId(Long.parseLong(tableIdAsString));
        tableBuilder.id(tableId);

        if (values.containsKey(TABLE_MAX_SEATS_ATTR_NAME)) {
            tableBuilder.maxSeats(Integer.parseInt(values.get(TABLE_MAX_SEATS_ATTR_NAME).n()));
        }

        return tableBuilder.build();
    }
}
