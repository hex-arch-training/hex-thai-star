package io.github.hexarchtraining.hts.common.port.out;

public interface TransactionPort {

    <T> T inTransaction(TransactionalMapper<T> handler);

    default void withTransaction(TransactionalConsumer consumer) {
        inTransaction(() -> {
            consumer.consume();
            return null;
        });
    }
}
