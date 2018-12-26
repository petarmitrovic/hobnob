package com.neperix.hobnob.iam;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Transaction;
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

        graphDb.execute("CREATE (p:Person { name: 'Petar Mitrovic', username: 'petar.mitrovic', email: 'petar.mitrovic@hobnob.com', role: 'Developer' })");
        graphDb.execute("CREATE (p:Person { name: 'Ragnar Lothbrok', username: 'ragnar.lothbrok', email: 'ragnar.lothbrok@hobnob.com', role: 'QA' })");
    }

    @Test
    public void itShouldFetchUserByUsernameIfExists() {
        Optional<User> user = userRepository.findByUsername("petar.mitrovic");
        assertThat(user.isPresent(), equalTo(true));
        assertThat(user.get(),
                allOf(
                        withUserName("petar.mitrovic"),
                        withEmail("petar.mitrovic@hobnob.com")
                )
        );
    }

    @Test
    public void itShouldFetchAllUsers() {
        List<User> allUsers = userRepository.findAll();
        assertThat(allUsers, hasSize(2));
    }

    @Test
    public void itShouldSaveUser() {

        User lagertha = User.builder()
                .username("lagertha")
                .email("lagertha@hobnob.com")
                .build();
        userRepository.save(lagertha);

        // since the save operation is async it might take some time for
        while (userRepository.findAll().size() < 3) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Transaction tx = graphDb.beginTx();
        Map<String, Object> lagerthaFromDb = graphDb.findNode(Label.label("Person"), "username", "lagertha").getAllProperties();
        assertThat(lagerthaFromDb, allOf(
                hasEntry("email", "lagertha@hobnob.com"),
                hasEntry("username", "lagertha")
        ));
        assertThat(lagerthaFromDb, hasKey("email"));
        assertThat(lagerthaFromDb.get("email"), equalTo("lagertha@hobnob.com"));
        tx.close();
    }

    private Matcher<User> withUserName(String username) {
        return new FeatureMatcher<User, String>(equalToIgnoringCase(username), "username", "username") {
            @Override
            protected String featureValueOf(User actual) {
                return actual.getUsername();
            }
        };
    }

    private Matcher<User> withEmail(String email) {
        return new FeatureMatcher<User, String>(equalToIgnoringCase(email), "user email", "user email") {
            @Override
            protected String featureValueOf(User actual) {
                return actual.getEmail();
            }
        };
    }
}
