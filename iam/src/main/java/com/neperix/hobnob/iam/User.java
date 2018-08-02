package com.neperix.hobnob.iam;

import java.util.HashSet;

/**
 * @author petarmitrovic
 */
public class User {

    private Long id;
    private String username;
    private String email;
    private HashSet<String> roles;

    public String getUsername() {
        return username;
    }
}
