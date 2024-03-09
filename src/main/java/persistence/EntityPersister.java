package persistence;

import jdbc.JdbcTemplate;
import persistence.sql.dml.DMLGenerator;

/**
 * EntityPersister는 엔터티의 메타데이터와 데이터베이스 매핑 정보를 제공하고, 변경된 엔터티를 데이터베이스에 동기화하는 역할
 */
public class EntityPersister {

    private final JdbcTemplate jdbcTemplate;
    private final DMLGenerator dmlGenerator;

    public EntityPersister(JdbcTemplate jdbcTemplate, DMLGenerator dmlGenerator) {
        this.jdbcTemplate = jdbcTemplate;
        this.dmlGenerator = dmlGenerator;
    }

    public void insert(Object entity) {
        String sql = dmlGenerator.generateInsert(entity);
        jdbcTemplate.execute(sql);
    }
}

