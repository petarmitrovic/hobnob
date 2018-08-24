package com.neperix.hobnob.rest;

import java.util.Set;

import lombok.Data;

/**
 * @author petarmitrovic
 */
@Data
public class CreateUserCommand {
    private String uuid;
    private String username;
    private String email;
    private Set<String> roles;
}
