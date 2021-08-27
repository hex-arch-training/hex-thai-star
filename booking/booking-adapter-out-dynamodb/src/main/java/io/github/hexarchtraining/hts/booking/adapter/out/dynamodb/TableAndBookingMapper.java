package io.github.hexarchtraining.hts.booking.adapter.out.dynamodb;

import io.github.hexarchtraining.hts.booking.domain.*;
import lombok.Getter;
import lombok.NonNull;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class TableAndBookingMapper {
    private static final String TABLE_ID_PREFIX = "T#";
    private static final String BOOKING_TOKEN_PREFIX = "B#";

    private static final String PK_ATTR_NAME = "PK";
    private static final String SK_ATTR_NAME = "SK";

    private static final String BOOKING_ID_ATTR_NAME = "id";
    private static final String BOOKING_CREATION_DATE_ATTR_NAME = "creationDate";
    private static final String BOOKING_FROM_TIME_ATTR_NAME = "bookingFromTime";
    private static final String BOOKING_TO_TIME_ATTR_NAME = "bookingToTime";
    private static final String BOOKING_DATE_ATTR_NAME = "bookingDate";
    private static final String BOOKING_EXPIRATION_DATE_ATTR_NAME = "expirationDate";
    private static final String BOOKING_EMAIL_ATTR_NAME = "email";
    private static final String BOOKING_SEATS_NUMBER_ATTR_NAME = "seatsNumber";
    private static final String BOOKING_STATUS_ATTR_NAME = "status";

    private static final String TABLE_MAX_SEATS_ATTR_NAME = "maxSeats";

    @Getter
    private final List<Table> tables = new ArrayList<>();
    @Getter
    private final List<Booking> bookings = new ArrayList<>();

    public List<Map<String, AttributeValue>> mapToAdjacencyList(@NonNull Booking booking) {
        final List<Map<String, AttributeValue>> adjacencyList = new ArrayList<>();
        Map<String, AttributeValue> bookingRepresentation = new HashMap<>();
        final AttributeValue bookingKey = AttributeValue.builder().s(BOOKING_TOKEN_PREFIX.concat(booking.getToken())).build();
        bookingRepresentation.put(PK_ATTR_NAME, bookingKey);
        bookingRepresentation.put(SK_ATTR_NAME, bookingKey);
        bookingRepresentation.put(BOOKING_ID_ATTR_NAME, AttributeValue.builder().n(String.valueOf(booking.getId().getValue())).build());
        bookingRepresentation.put(BOOKING_CREATION_DATE_ATTR_NAME, AttributeValue.builder().s(booking.getCreationDate().toString()).build());
        bookingRepresentation.put(BOOKING_FROM_TIME_ATTR_NAME, AttributeValue.builder().s(booking.getBookingFromTime().toString()).build());
        bookingRepresentation.put(BOOKING_TO_TIME_ATTR_NAME, AttributeValue.builder().s(booking.getBookingToTime().toString()).build());
        bookingRepresentation.put(BOOKING_DATE_ATTR_NAME, AttributeValue.builder().s(booking.getBookingDate().toString()).build());
        bookingRepresentation.put(BOOKING_EXPIRATION_DATE_ATTR_NAME, AttributeValue.builder().s(booking.getExpirationDate().toString()).build());
        bookingRepresentation.put(BOOKING_EMAIL_ATTR_NAME, AttributeValue.builder().s(booking.getEmail()).build());
        bookingRepresentation.put(BOOKING_SEATS_NUMBER_ATTR_NAME, AttributeValue.builder().n(String.valueOf(booking.getSeatsNumber())).build());
        bookingRepresentation.put(BOOKING_STATUS_ATTR_NAME, AttributeValue.builder().s(booking.getStatus().toString()).build());

        adjacencyList.add(bookingRepresentation);

        List<Map<String, AttributeValue>> tableBookingRepresentations = booking.getTableBookings().stream().map(tableBooking -> {
            Map<String, AttributeValue> tableBookingRepresentation = new HashMap<>();
            tableBookingRepresentation.put(PK_ATTR_NAME, bookingKey);
            final String tableKey = TABLE_ID_PREFIX.concat(String.valueOf(tableBooking.getTableId().getValue()));
            tableBookingRepresentation.put(SK_ATTR_NAME, AttributeValue.builder().s(tableKey).build());
            tableBookingRepresentation.put(BOOKING_SEATS_NUMBER_ATTR_NAME, AttributeValue.builder().n(String.valueOf(tableBooking.getSeatsNumber())).build());

            return tableBookingRepresentation;
        }).collect(Collectors.toList());

        adjacencyList.addAll(tableBookingRepresentations);

        return adjacencyList;
    }

    public TableAndBookingMapper parse(@NonNull List<Map<String, AttributeValue>> adjacencyList) {
        Map<String, List<Map<String, AttributeValue>>> tablesAndBookingsByTheirIds = adjacencyList.
                stream().collect(Collectors.groupingBy(singleTableAndBookingAttrs -> {
            if (!singleTableAndBookingAttrs.containsKey(PK_ATTR_NAME)) {
                throw new IllegalArgumentException("PK attribute is missing!");
            }
            if (!singleTableAndBookingAttrs.containsKey(SK_ATTR_NAME)) {
                throw new IllegalArgumentException("SK attribute is missing!");
            }
            return singleTableAndBookingAttrs.get(PK_ATTR_NAME).s();
        }));

        tablesAndBookingsByTheirIds.forEach((primaryKey, tableOrBookingAttrs) -> {
            if (primaryKey.startsWith(TABLE_ID_PREFIX)) {
                if (tableOrBookingAttrs.size() != 1) {
                    throw new IllegalStateException("PK is unique for tables");
                }
                tables.add(createTable(tableOrBookingAttrs.get(0)));
            } else if (primaryKey.startsWith(BOOKING_TOKEN_PREFIX)) {
                createBookingAlongWithTableBookings(tableOrBookingAttrs);
            } else {
                throw new IllegalStateException("Unknown PK prefix :".concat(primaryKey));
            }
        });

        return this;
    }

    private void createBookingAlongWithTableBookings(@NonNull List<Map<String, AttributeValue>> attrsOfBookings) {
        final Map<Boolean, List<Map<String, AttributeValue>>> bookingAndTableBookings = attrsOfBookings.stream()
                .collect(Collectors.partitioningBy(attrsOfBookingOrTableBookings -> {
                    final String primaryKey = attrsOfBookingOrTableBookings.get(PK_ATTR_NAME).s();
                    final String sortKey = attrsOfBookingOrTableBookings.get(SK_ATTR_NAME).s();
                    return primaryKey.equals(sortKey); // represents a booking
                }));

        final List<Map<String, AttributeValue>> bookingOutOfTableBookings = bookingAndTableBookings.get(Boolean.TRUE);
        if (bookingOutOfTableBookings.size() != 1) {
            throw new IllegalStateException("No booking found!");
        }
        final Booking booking = createBooking(bookingOutOfTableBookings.get(0));
        final List<Map<String, AttributeValue>> tableBookings = bookingAndTableBookings.get(Boolean.FALSE);
        tableBookings.forEach(tableBookingAttrs -> {
            final String sortKey = tableBookingAttrs.get(SK_ATTR_NAME).s();
            final Table tableBooking = Table.builder()
                    .id(parseTableIdFromKey(sortKey))
                    .maxSeats(parseInt(BOOKING_SEATS_NUMBER_ATTR_NAME, tableBookingAttrs))
                    .build();
            booking.addTableBooking(tableBooking);
        });
    }

    private Booking createBooking(@NonNull Map<String, AttributeValue> bookingAttrs) {
        final String primaryKey = bookingAttrs.get(PK_ATTR_NAME).s();
        final String bookingToken = primaryKey.substring(BOOKING_TOKEN_PREFIX.length());
        final BookingId bookingId = new BookingId(parseLong(BOOKING_ID_ATTR_NAME, bookingAttrs));
        final Instant bookingCreationDate = parseDate(BOOKING_CREATION_DATE_ATTR_NAME, bookingAttrs);
        final Instant bookingFromTime = parseDate(BOOKING_FROM_TIME_ATTR_NAME, bookingAttrs);
        final Instant bookingToTime = parseDate(BOOKING_TO_TIME_ATTR_NAME, bookingAttrs);
        final Instant bookingDate = parseDate(BOOKING_DATE_ATTR_NAME, bookingAttrs);
        final Instant bookingExpirationDate = parseDate(BOOKING_EXPIRATION_DATE_ATTR_NAME, bookingAttrs);
        final String bookingEmail = bookingAttrs.get(BOOKING_EMAIL_ATTR_NAME).s();
        final String bookingStatus = bookingAttrs.get(BOOKING_STATUS_ATTR_NAME).s();

        final int bookingSeatsNumber = parseInt(BOOKING_SEATS_NUMBER_ATTR_NAME, bookingAttrs);

        return Booking.createNewBooking(bookingId, bookingCreationDate, bookingFromTime, bookingToTime, bookingDate,
                bookingExpirationDate, bookingEmail, bookingSeatsNumber, BookingStatus.valueOf(bookingStatus), bookingToken);
    }

    private Instant parseDate(String dateAttrName, Map<String, AttributeValue> attrs) {
        final String dateInIso = attrs.get(dateAttrName).s();
        return Instant.parse(dateInIso);
    }

    private long parseLong(String dateAttrName, Map<String, AttributeValue> attrs) {
        final String numberAsString = attrs.get(dateAttrName).n();
        return Long.parseLong(numberAsString);
    }

    private int parseInt(String dateAttrName, Map<String, AttributeValue> attrs) {
        final String numberAsString = attrs.get(dateAttrName).n();
        return Integer.parseInt(numberAsString);
    }

    private TableId parseTableIdFromKey(String key) {
        final String tableIdAsString = key.substring(TABLE_ID_PREFIX.length());
        return new TableId(Long.parseLong(tableIdAsString));
    }

    private Table createTable(@NonNull Map<String, AttributeValue> tableAttrs) {
        final Table.TableBuilder tableBuilder = Table.builder();

        final String primaryKey = tableAttrs.get(PK_ATTR_NAME).s();
        tableBuilder.id(parseTableIdFromKey(primaryKey));

        if (tableAttrs.containsKey(TABLE_MAX_SEATS_ATTR_NAME)) {
            tableBuilder.maxSeats(Integer.parseInt(tableAttrs.get(TABLE_MAX_SEATS_ATTR_NAME).n()));
        }

        return tableBuilder.build();
    }
}
