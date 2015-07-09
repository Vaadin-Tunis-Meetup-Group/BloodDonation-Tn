package org.vaadin.tunis.blooddonation;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class BloodDonationTnApplication {
	@Configuration
	@EnableTransactionManagement
	@EnableNeo4jRepositories(basePackages = "org.vaadin.tunis.blooddonation.persistence.repository")
	static class ApplicationConfig extends Neo4jConfiguration {

		public ApplicationConfig() {
			setBasePackage("org.vaadin.tunis.blooddonation.persistence");
		}

		@Bean
		GraphDatabaseService graphDatabaseService() {

//			return new SpringRestGraphDatabase(
//					"http://localhost:7474/db/data");
//			return new SpringRestGraphDatabase(
//					"https://neo-55911f388b7e6-364459c455.do-stories.graphstory.com:7473/db/data",
//					"55911f388b7e6", "t7TjTuw7ZQ8I2s5ip5Fa8VOBK9WgUBDPIu5JDC0n");

			 return new GraphDatabaseFactory()
			 .newEmbeddedDatabase("bloodDonationTn.db");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(BloodDonationTnApplication.class, args);
	}
}
