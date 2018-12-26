package com.neperix.hobnob.iam;

import java.util.List;
import java.util.Optional;

/**
 * @author petarmitrovic
 */
interface UserRepository {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    void save(User user);
}
