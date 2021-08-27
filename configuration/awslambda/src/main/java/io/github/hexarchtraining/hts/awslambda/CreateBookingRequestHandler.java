package io.github.hexarchtraining.hts.awslambda;

import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.CreateBookingController;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Request;
import io.github.hexarchtraining.hts.booking.adapter.in.awslambda.common.Response;
import io.github.hexarchtraining.hts.booking.adapter.out.dynamodb.FindFreeTablesDynamoDbAdapter;
import io.github.hexarchtraining.hts.booking.adapter.out.dynamodb.SaveBookingDynamoDbAdapter;
import io.github.hexarchtraining.hts.booking.service.CreateBookingService;
import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import io.github.hexarchtraining.hts.common.port.out.TransactionalMapper;

public class CreateBookingRequestHandler extends AbstractRequestHandler {
    final CreateBookingController controller;

    public CreateBookingRequestHandler() {
        final SaveBookingDynamoDbAdapter saveBookingDynamoDbAdapter = new SaveBookingDynamoDbAdapter();

        controller = new CreateBookingController(
                new CreateBookingService(
                        saveBookingDynamoDbAdapter,
                        saveBookingDynamoDbAdapter,
                        new FindFreeTablesDynamoDbAdapter(),
                        event -> {
                        },
                        new TransactionPort() {
                            @Override
                            public <T> T inTransaction(TransactionalMapper<T> handler) {
                                return handler.accept();
                            }
                        }));
    }

    @Override
    protected Response handleRequest(Request request) {
        return controller.createBooking(request);
    }
}
