package io.github.hexarchtraining.hts.booking.adapter.out.dynamodb;

import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.domain.TableBooking;
import io.github.hexarchtraining.hts.booking.domain.TableId;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
import lombok.NonNull;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class FindFreeTablesDynamoDbAdapter extends AbstractDynamoDbAdapter implements FindFreeTablesPort {
    @Override
    public List<Table> find(@NonNull Instant from, @NonNull Instant to) {
        if (to.isBefore(from)) {
            throw new IllegalArgumentException();
        }

        DynamoDbClient ddb = getClient();
        ScanResponse response = ddb.scan(ScanRequest.builder().tableName(getDynamoDbTableName()).build());

        final TableAndBookingMapper mapper = new TableAndBookingMapper().parse(response.items());
        List<TableId> freeTableIds = mapper.getBookings().stream()
                .filter(booking -> from.isAfter(booking.getBookingToTime()) || to.isBefore(booking.getBookingFromTime()))
                .flatMap(booking -> booking.getTableBookings().stream().map(TableBooking::getTableId))
                .collect(Collectors.toList());

        return mapper.getTables().stream()
                .filter(table -> freeTableIds.contains(table.getId()))
                .collect(Collectors.toList());
    }
}
