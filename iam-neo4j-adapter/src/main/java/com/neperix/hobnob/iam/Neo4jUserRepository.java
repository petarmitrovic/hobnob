package com.neperix.hobnob.iam;

import java.util.List;

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
//                .id(rec.get("id").asLong())
                .username(rec.get("username").asString())
                .email(rec.get("email").asString())
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
    public User save(User user) {
        return null;
    }
}
