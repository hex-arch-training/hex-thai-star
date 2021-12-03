package io.github.hexarchtraining.hts.common.adapter.out.jdbi.dao;

import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface HibernateSequenceDao {

    @SqlUpdate("CREATE SEQUENCE hibernate_sequence " +
        "START WITH " +
        "1 INCREMENT BY 1")
    void createSequence();
}
