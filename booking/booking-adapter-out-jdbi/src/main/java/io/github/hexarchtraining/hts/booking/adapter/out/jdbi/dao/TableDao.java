package io.github.hexarchtraining.hts.booking.adapter.out.jdbi.dao;

import io.github.hexarchtraining.hts.booking.adapter.out.jdbi.record.TableRecord;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface TableDao {

    @SqlUpdate("CREATE TABLE table_entity (id bigint NOT NULL, " +
        "max_seats integer, " +
        "PRIMARY KEY (id))")
    void createTable();

    @SqlQuery("SELECT * FROM Table_Entity")
    @RegisterBeanMapper(TableRecord.class)
    List<TableRecord> findAll();

    @SqlUpdate("INSERT INTO " +
        "Table_Entity(id, max_seats) " +
        "VALUES (hibernate_sequence.nextval, :maxSeats)")
    @GetGeneratedKeys
    long insert(@BindBean TableRecord table);

}
