package io.github.hexarchtraining.hts.common.adapter.out;

import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import io.github.hexarchtraining.hts.common.port.out.TransactionalMapper;

public class TestTransactionAdapter implements TransactionPort {

    private int callCount = 0;

    @Override
    public <T> T inTransaction(TransactionalMapper<T> handler) {
        ++callCount;
        return handler.accept();
    }

    public boolean hasBeenCalled() {
        return callCount > 0;
    }

    public boolean hasBeenCalledTimes(int times) {
        return callCount == times;
    }

    public void reset() {
        callCount = 0;
    }
}
