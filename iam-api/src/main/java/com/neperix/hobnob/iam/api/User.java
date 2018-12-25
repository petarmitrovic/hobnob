package com.neperix.hobnob.iam.api;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author petarmitrovic
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String uuid;
    private String username;
    private Set<String> roles;
}
