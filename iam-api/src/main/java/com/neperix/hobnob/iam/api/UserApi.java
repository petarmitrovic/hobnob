package com.neperix.hobnob.iam.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author petarmitrovic
 */
public interface UserApi {

    /**
     * Get all the users.
     *
     * @return The list containing all users.
     */
    List<User> all();

    /**
     * Finds a user with given uuid.
     *
     * @param uuid The uuid to search user for.
     *
     * @return The user matching the given uuid, or an Optional.empty() if the user couldn't be found.
     */
    Optional<User> findOne(String uuid);

    /**
     * Finds a user with given username.
     *
     * @param username The username to search user for.
     * @return The user matching the given username, or an Optional.empty() if the user couldn't be found.
     */
    User findByUsername(String username);

    void create(String uuid, String username, String email, Set<String> roles);
}
