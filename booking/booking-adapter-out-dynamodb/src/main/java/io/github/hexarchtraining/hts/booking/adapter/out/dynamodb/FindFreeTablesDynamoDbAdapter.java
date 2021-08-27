package io.github.hexarchtraining.hts.booking.adapter.out.dynamodb;

import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.port.out.FindFreeTablesPort;
import lombok.NonNull;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.time.Instant;
import java.util.List;

public class FindFreeTablesDynamoDbAdapter extends AbstractDynamoDbAdapter implements FindFreeTablesPort {
    @Override
    public List<Table> find(@NonNull Instant from, @NonNull Instant to) {
        DynamoDbClient ddb = getClient();
        ScanResponse response = ddb.scan(ScanRequest.builder().tableName(getDynamoDbTableName()).build());
        final TableAndBookingMapper mapper = new TableAndBookingMapper().parse(response.items());
        return new FreeTablesFinder(mapper.getBookings(), mapper.getTables()).find(from, to);
    }
}
