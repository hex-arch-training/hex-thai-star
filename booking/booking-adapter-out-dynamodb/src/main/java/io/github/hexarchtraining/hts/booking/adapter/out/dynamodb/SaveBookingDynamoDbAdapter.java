package io.github.hexarchtraining.hts.booking.adapter.out.dynamodb;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import io.github.hexarchtraining.hts.booking.domain.Booking;
import io.github.hexarchtraining.hts.booking.domain.BookingId;
import io.github.hexarchtraining.hts.booking.port.out.PersistBookingPort;
import io.github.hexarchtraining.hts.booking.port.out.SaveBookingPort;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.Put;
import software.amazon.awssdk.services.dynamodb.model.TransactWriteItem;
import software.amazon.awssdk.services.dynamodb.model.TransactWriteItemsRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.DEFAULT_NUMBER_GENERATOR;

public class SaveBookingDynamoDbAdapter extends AbstractDynamoDbAdapter implements SaveBookingPort, PersistBookingPort {
    private static final char[] ID_ALPHABET = "123456789".toCharArray();
    private static final int ID_LENGTH = 10;

    private static long nextId() {
        final String nextIdAsString = NanoIdUtils.randomNanoId(DEFAULT_NUMBER_GENERATOR, ID_ALPHABET, ID_LENGTH);
        return Long.parseLong(nextIdAsString);
    }

    @Override
    public Booking persist(Booking booking) {
        BookingId bookingId = new BookingId(nextId());

        final Booking bookingToPersist = Booking.createNewBooking(
                bookingId,
                booking.getCreationDate(),
                booking.getBookingFromTime(),
                booking.getBookingToTime(),
                booking.getBookingDate(),
                booking.getExpirationDate(),
                booking.getEmail(),
                booking.getSeatsNumber(),
                booking.getStatus(),
                booking.getToken()
        );
        saveOrUpdate(bookingToPersist);

        return bookingToPersist;
    }

    @Override
    public void save(Booking booking) {
        saveOrUpdate(booking);
    }

    private void saveOrUpdate(Booking booking) {
        final List<Map<String, AttributeValue>> items = new TableAndBookingMapper().mapToAdjacencyList(booking);

        List<TransactWriteItem> transactWriteItems = items.stream().map(item -> TransactWriteItem.builder().put(
                Put.builder()
                        .tableName(getDynamoDbTableName())
                        .item(item)
                        .build()).build()).collect(Collectors.toList());

        getClient().transactWriteItems(TransactWriteItemsRequest.builder()
                .transactItems(transactWriteItems)
                .build());
    }
}
