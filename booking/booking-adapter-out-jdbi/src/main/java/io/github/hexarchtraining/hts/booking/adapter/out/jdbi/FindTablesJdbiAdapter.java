package io.github.hexarchtraining.hts.booking.adapter.out.jdbi;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao.TableDao;
import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.mapper.TableMapper;
import io.github.hexarchtraining.hts.booking.domain.Table;
import io.github.hexarchtraining.hts.booking.port.out.FindTablesPort;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FindTablesJdbiAdapter implements FindTablesPort {

    private final Jdbi db;

    private final TableMapper tableMapper = TableMapper.INSTANCE;

    @Override
    public List<Table> findTables() {
        return db.withHandle(handle -> {
            final TableDao dao = handle.attach(TableDao.class);
            return dao.findAll();
        }).stream()
                .map(tableMapper::toDomain)
                .collect(Collectors.toList());
    }
}
