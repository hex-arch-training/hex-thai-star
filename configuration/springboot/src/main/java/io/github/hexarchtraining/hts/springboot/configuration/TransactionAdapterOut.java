package io.github.hexarchtraining.hts.springboot.configuration;

import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import io.github.hexarchtraining.hts.common.port.out.TransactionalMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionAdapterOut implements TransactionPort {
    @Override
    @Transactional
    public <T> T inTransaction(TransactionalMapper<T> handler) {
        return handler.accept();
    }
}
