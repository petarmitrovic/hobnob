package com.neperix.hobnob.iam;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author petarmitrovic
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IamNeo4jConfig.class, EmbeddedNeo4jConfig.class })
@ActiveProfiles("it")
public class Neo4jUserRepositoryTest {

    @Autowired
    private GraphDatabaseService graphDb;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        graphDb.execute("MATCH (n) DETACH DELETE n");

        graphDb.execute("CREATE (p:Person { name: 'Petar Mitrovic', username: 'petar.mitrovic', role: 'Developer' })");
        graphDb.execute("CREATE (p:Person { name: 'Ragnar Lothbrok', username: 'ragnar.lothbrok', role: 'QA' })");
    }

    @Test
    public void test() {
        assertThat(graphDb, notNullValue());
    }

    @Test
    public void itShouldFetchAllUsers() {
        List<User> allUsers = userRepository.findAll();
        assertThat(allUsers, hasSize(2));
    }
}
