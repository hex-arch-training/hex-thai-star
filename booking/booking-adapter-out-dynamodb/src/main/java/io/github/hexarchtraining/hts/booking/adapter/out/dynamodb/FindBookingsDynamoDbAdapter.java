package io.github.hexarchtraining.hts.booking.adapter.out.dynamodb;

import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.port.out.FindBookingsPort;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.List;

public class FindBookingsDynamoDbAdapter extends AbstractDynamoDbAdapter implements FindBookingsPort {
    @Override
    public List<Booking> findBookings() {
        ScanResponse response = getClient().scan(ScanRequest.builder()
                .tableName(getDynamoDbTableName())
                .build());
        return new TableAndBookingMapper().parse(response.items()).getBookings();
    }
}
