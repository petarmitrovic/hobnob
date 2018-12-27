package com.neperix.hobnob.iam;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;

import lombok.AllArgsConstructor;

/**
 * @author petarmitrovic
 */
@AllArgsConstructor
public class Neo4jUserRepository implements UserRepository {

    private final Driver driver;

    private static User recToUser(Record rec) {
        return User.builder()
                .username(rec.get("p").get("username").asString())
                .email(rec.get("p").get("email").asString())
                .build();
    }

    @Override
    public List<User> findAll() {
        StatementResult result = driver.session().run("MATCH (p:Person) RETURN p");
        return result.list(Neo4jUserRepository::recToUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        StatementResult result = driver.session().run(
                "MATCH (p:Person) WHERE p.username = $username RETURN p",
                Collections.singletonMap("username", username));

        return Optional.ofNullable(result.single())
                .map(Neo4jUserRepository::recToUser);
    }

    @Override
    public void save(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("email", user.getEmail());

        driver.session().run("CREATE (n:Person {username: $username, email: $email}) RETURN n", params);
    }
}
