package com.neperix.hobnob.iam;

import java.util.List;

/**
 * @author petarmitrovic
 */
interface UserRepository {

    List<User> findAll();

    List<User> findById(Long id);

    List<User> findByUsername(String username);

    void save(User user);
}
