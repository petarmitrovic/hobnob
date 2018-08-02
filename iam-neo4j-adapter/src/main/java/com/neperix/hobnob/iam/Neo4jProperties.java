package com.neperix.hobnob.iam;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author petarmitrovic
 */
@Data
@ConfigurationProperties(prefix = "iam.neo4j")
public class Neo4jProperties {
    private String uri;
    private String username;
    private String password;
}
