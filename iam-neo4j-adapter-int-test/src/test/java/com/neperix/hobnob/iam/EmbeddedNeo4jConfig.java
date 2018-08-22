package com.neperix.hobnob.iam;

import java.io.File;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.kernel.configuration.BoltConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * @author petarmitrovic
 */
@Configuration
class EmbeddedNeo4jConfig {

    @Bean
    GraphDatabaseService graphDb() {

        File file = new File("/tmp/hobnob-iam");
        if (!file.exists()) {
            file.mkdir();
        }

        BoltConnector bolt = new BoltConnector("0");
//        GraphDatabaseService db = new GraphDatabaseFactory()
//                .newEmbeddedDatabaseBuilder(file)
//                .setConfig( bolt.type, "BOLT" )
//                .setConfig( bolt.enabled, "true" )
//                .setConfig( bolt.listen_address, "localhost:7687")
//                .newGraphDatabase();

        GraphDatabaseService db = new GraphDatabaseFactory()
                .newEmbeddedDatabaseBuilder(file)
                .setConfig(GraphDatabaseSettings.pagecache_memory, "512M")
                .setConfig(GraphDatabaseSettings.string_block_size, "60")
                .setConfig(GraphDatabaseSettings.array_block_size, "300")
                .setConfig(GraphDatabaseSettings.store_internal_log_level, "DEBUG")
//                .loadPropertiesFromFile(path.toAbsolutePath().toString())
                .setConfig(bolt.enabled, "true")
                .setConfig(bolt.type, "BOLT")
                .setConfig(bolt.listen_address, "0.0.0.0:7687")
                .setConfig(bolt.encryption_level, BoltConnector.EncryptionLevel.OPTIONAL.toString())
//                .setConfig(ShellSettings.remote_shell_enabled, Settings.TRUE)
                .newGraphDatabase();

        Runtime.getRuntime().addShutdownHook(new Thread(db::shutdown));
        return db;
    }

    @Primary
    @Profile("it")
    @Bean
    Driver neo4jDriver(GraphDatabaseService dbService) {
        return GraphDatabase.driver("bolt://127.0.0.1:7687");
    }
}
