package io.github.hexarchtraining.hts.springboot.configuration;

import io.github.hexarchtraining.hts.common.port.out.TransactionPort;
import io.github.hexarchtraining.hts.common.port.out.TransactionalMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * This port implementation should reside in common-adapter-out-jpa module (which doesn't exist yet) and have no
 * Spring dependencies (@Service!). Instead, it should be configured as any other adapter implementation in Springboot
 * app configuration.
 */
@Service
public class TransactionAdapterOut implements TransactionPort {
    @Override
    @Transactional
    public <T> T inTransaction(TransactionalMapper<T> handler) {
        return handler.accept();
    }
}
