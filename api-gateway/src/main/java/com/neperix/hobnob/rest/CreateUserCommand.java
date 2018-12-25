package com.neperix.hobnob.rest;

import java.util.Set;

import lombok.Data;

@Data
public class CreateUserCommand {
    private String username;
    private String email;
    private Set<String> roles;
}
