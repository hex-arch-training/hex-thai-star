package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableRecord;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface TableDao {

    @SqlQuery("SELECT * FROM Table")
    List<TableRecord> findAll();
}
