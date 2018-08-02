package com.neperix.hobnob.iam;

import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author petarmitrovic
 */
@Configuration
class EmbeddedNeo4jConfig {

    @Bean
    GraphDatabaseService graphDb() {

        File file = new File("/tmp/hobnob-iam");
        if (file.exists()) {
            file.mkdir();
        }


        GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(file);
        Runtime.getRuntime().addShutdownHook(new Thread(db::shutdown));
        return db;
    }
}
