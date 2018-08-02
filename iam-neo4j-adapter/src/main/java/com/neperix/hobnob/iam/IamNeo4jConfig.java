package com.neperix.hobnob.iam;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author petarmitrovic
 */
@Configuration
@PropertySource("classpath:/hobnob-iam.yml")
public class IamNeo4jConfig {

//    @Bean
//    Driver neo4jDriver(Neo4jProperties config) {
//        return GraphDatabase.driver(config.getUri(), AuthTokens.basic(config.getUsername(), config.getPassword()));
//    }

    @Bean
    Driver embeddedDriver() {
        return GraphDatabase.driver("bolt://localhost:7687");
    }

    @Bean
    Neo4jProperties neo4jProperties() {
        return new Neo4jProperties();
    }
}
