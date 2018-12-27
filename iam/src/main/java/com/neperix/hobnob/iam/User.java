package com.neperix.hobnob.iam;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;

/**
 * @author petarmitrovic
 */
@Getter
@Builder
class User {

    private Long id;
    private String username;
    private String email;
    private Set<String> roles;

    void setRoles(Set<Long> roles) {
        this.roles = Optional.ofNullable(roles)
                .map(set -> set.stream().map(String::valueOf).collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
    }
}
