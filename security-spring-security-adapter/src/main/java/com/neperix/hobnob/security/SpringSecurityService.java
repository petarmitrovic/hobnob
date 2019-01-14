package com.neperix.hobnob.security;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SpringSecurityService implements SecurityService {

    private final AuthenticationManager authenticationManager;
    private final AuthenticatedUserRepository authenticatedUserRepository;

    @Override
    public Optional<AuthenticatedUser> getAuthenticatedUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(AuthenticatedUser.class::cast);
    }

    @Override
    public Optional<AuthenticatedUser> find(String username) {
        return authenticatedUserRepository.findByUsername(username);
    }

    @Override
    public Optional<AuthenticatedUser> authenticate(String username, String password) {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authenticatedUserRepository.findByUsername(username)
                .map(SpringAuthenticatedUser::of);
    }

    @Override
    public void loadToContext(AuthenticatedUser authUser) {

        // TODO: think of passing encrypted credential as an argument
        SpringAuthenticatedUser springUser = SpringAuthenticatedUser.of(authUser);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                springUser, "", springUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
