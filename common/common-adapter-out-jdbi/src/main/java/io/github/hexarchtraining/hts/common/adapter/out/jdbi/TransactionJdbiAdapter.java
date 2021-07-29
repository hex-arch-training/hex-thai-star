package io.github.hexarchtraining.hts.common.adapter.out.jdbi;

import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import io.github.hexarchtraining.hts.common.port.out.TransactionalMapper;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

@AllArgsConstructor
public class TransactionJdbiAdapter implements TransactionPort {

    private final Jdbi db;

    @Override
    public <T> T inTransaction(TransactionalMapper<T> handler) {
        return db.inTransaction(handle -> handler.accept());
    }
}
