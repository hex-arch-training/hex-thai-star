package io.github.hexarchtraining.hts.booking.adapter.out.dynamodb;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

import java.net.URI;

public class AbstractDynamoDbAdapter {
    public static final URI LOCAL_DYNAMODB_ENDPOINT_URI = URI.create("http://localhost:8000");

    private DynamoDbClient client;

    protected DynamoDbClient getClient() {
        if (client == null) {
            final DynamoDbClientBuilder clientBuilder = DynamoDbClient.builder();
            final String lambdaAwsRegion = System.getenv("AWS_REGION");
            if (lambdaAwsRegion == null) {
                clientBuilder.endpointOverride(LOCAL_DYNAMODB_ENDPOINT_URI);
            } else {
                clientBuilder.region(Region.of(lambdaAwsRegion));
            }
            client = clientBuilder.build();
        }
        return client;
    }

    protected String getDynamoDbTableName() {
        final String dynamoDbTableName = System.getenv("AWS_TABLE_AND_BOOKING_TABLE");
        if (dynamoDbTableName == null) {
            throw new NullPointerException("No table name found under AWS_TABLE_AND_BOOKING_TABLE env variable");
        }
        return dynamoDbTableName;
    }
}
