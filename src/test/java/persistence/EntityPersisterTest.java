package persistence;

import database.DatabaseServer;
import database.H2;
import domain.Person;
import jdbc.JdbcTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.sql.ddl.DDLGenerator;
import persistence.sql.dml.DMLGenerator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class EntityPersisterTest {

    private DatabaseServer server;
    private JdbcTemplate jdbcTemplate;
    private EntityPersister entityPersister;
    private EntityManager entityManager;

    DDLGenerator ddlGenerator = new DDLGenerator(Person.class);
    DMLGenerator dmlGenerator = new DMLGenerator(Person.class);

    @BeforeEach
    void setUp() throws SQLException {
        server = new H2();
        server.start();

        jdbcTemplate = new JdbcTemplate(server.getConnection());
        jdbcTemplate.execute(ddlGenerator.generateCreate());

        entityPersister = new EntityPersister(jdbcTemplate, dmlGenerator);
        entityManager = new DefaultEntityManager(jdbcTemplate, dmlGenerator);
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute(ddlGenerator.generateDrop());
        server.stop();
    }

    @Test
    @DisplayName("Person을 저장한다.")
    void insert() {
        // given
        String name = "name";
        Person person = new Person(name, 26, "email", 1);

        // when
        entityPersister.insert(person);

        // then
        Person result = entityManager.find(Person.class, 1L);
        assertThat(result.getName()).isEqualTo(name);
    }
}
