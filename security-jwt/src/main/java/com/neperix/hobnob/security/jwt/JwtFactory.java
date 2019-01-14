package com.neperix.hobnob.security.jwt;

import java.util.Optional;

import com.neperix.hobnob.security.AuthenticatedUser;

interface JwtFactory {

    String generate(AuthenticatedUser user);

    String refresh(String token);

    Optional<Token> decode(String token);
}
