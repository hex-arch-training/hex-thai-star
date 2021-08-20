package io.github.hexarchtraining.hts.booking.adapter.out.dynamodb;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.port.out.FindTablesPort;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.List;
import java.util.stream.IntStream;

import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.DEFAULT_NUMBER_GENERATOR;

public class FindTablesDynamoDbAdapter extends AbstractDynamoDbAdapter implements FindTablesPort {
    @Override
    public List<Table> findTables() {
        DynamoDbClient ddb = getClient();
        ScanResponse response = ddb.scan(ScanRequest.builder().tableName(getDynamoDbTableName()).build());
        return new TableAndBookingMapper().from(response.items()).getTables();
    }

    public static void main(String[] args) {
        System.out.println(new FindTablesDynamoDbAdapter().findTables());
        IntStream.range(1, 9).mapToObj(current -> NanoIdUtils.randomNanoId(DEFAULT_NUMBER_GENERATOR, "123456789".toCharArray(), 10)).forEach(System.out::println);
    }
}
