package com.neperix.hobnob.security;

import java.util.Optional;

public interface AuthenticatedUserRepository {

    Optional<AuthenticatedUser> findByUsername(String username);
}
