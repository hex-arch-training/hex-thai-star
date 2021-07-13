package io.github.hexarchtraining.hts.common.port.out;

@FunctionalInterface
public interface TransactionalMapper<T> {
    T accept();
}
