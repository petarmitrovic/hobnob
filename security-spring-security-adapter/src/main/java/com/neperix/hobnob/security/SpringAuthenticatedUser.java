package com.neperix.hobnob.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

class SpringAuthenticatedUser implements AuthenticatedUser, UserDetails {

    private static final String ROLE_PREFIX = "ROLE_";

    private final String username;
    private final Set<? extends GrantedAuthority> authorities;

    private SpringAuthenticatedUser(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = Collections.unmodifiableSet(new HashSet<>(authorities));
    }

    static SpringAuthenticatedUser of(AuthenticatedUser other) {
        List<SimpleGrantedAuthority> authorities = other.roles().stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .collect(Collectors.toList());
        return new SpringAuthenticatedUser(other.username(), authorities);
    }

    @Override
    public String username() {
        return this.username;
    }

    @Override
    public Set<String> roles() {
        return this.authorities.stream()
                .map(auth -> ((GrantedAuthority) auth).getAuthority().replace(ROLE_PREFIX, ""))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
