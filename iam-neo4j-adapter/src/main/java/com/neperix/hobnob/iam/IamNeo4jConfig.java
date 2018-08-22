package com.neperix.hobnob.iam;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author petarmitrovic
 */
@Configuration
@EnableConfigurationProperties(Neo4jProperties.class)
@PropertySource("classpath:/hobnob-iam.properties")
public class IamNeo4jConfig {

    @Bean
    Driver neo4jDriver(Neo4jProperties config) {
        return GraphDatabase.driver(config.getUri(), AuthTokens.basic(config.getUsername(), config.getPassword()));
    }

    @Bean
    UserRepository userRepository(Driver driver) {
        return new Neo4jUserRepository(driver);
    }
}
