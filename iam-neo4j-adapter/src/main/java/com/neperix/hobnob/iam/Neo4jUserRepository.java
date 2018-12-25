package com.neperix.hobnob.iam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.StatementResult;

import lombok.AllArgsConstructor;

/**
 * @author petarmitrovic
 */
@AllArgsConstructor
public class Neo4jUserRepository implements UserRepository {

    private final Driver driver;

    @Override
    public List<User> findAll() {
        StatementResult result = driver.session().run("MATCH (p:Person) RETURN p");
        return result.list(rec -> User.builder()
            .uuid(rec.get("p").get("uuid").asString())
            .username(rec.get("p").get("username").asString())
            .email(rec.get("p").get("email").asString())
            .build());
    }

    @Override
    public List<User> findById(Long id) {
        return null;
    }

    @Override
    public List<User> findByUsername(String username) {
        return null;
    }

    @Override
    public void save(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("uuid", user.getUuid());
        params.put("username", user.getUsername());
        params.put("email", user.getEmail());
        driver.session().run(createUserCypher(user));
    }

    private String createUserCypher(User user) {
        return new StringBuilder()
                .append(" CREATE (n:Person { ")
                .append(" uuid: '").append(user.getUuid()).append("', ")
                .append(" username: '").append(user.getUsername()).append("', ")
                .append(" email: '").append(user.getEmail()).append("' ")
                .append(" }) ")
                .append(" RETURN n")
                .toString();
    }
}
