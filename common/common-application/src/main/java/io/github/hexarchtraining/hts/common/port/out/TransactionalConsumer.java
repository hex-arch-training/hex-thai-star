package io.github.hexarchtraining.hts.common.port.out;

@FunctionalInterface
public interface TransactionalConsumer {
    void consume();
}
