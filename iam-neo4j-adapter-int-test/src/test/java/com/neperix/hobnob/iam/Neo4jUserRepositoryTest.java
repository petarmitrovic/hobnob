package com.neperix.hobnob.iam;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author petarmitrovic
 */
@ContextConfiguration(classes = { IamNeo4jConfig.class, EmbeddedNeo4jConfig.class })
@RunWith(SpringRunner.class)
public class Neo4jUserRepositoryTest {

    @Autowired
    private GraphDatabaseService graphDb;

    @Test
    public void test() {
        assertThat(graphDb, notNullValue());
    }
}
