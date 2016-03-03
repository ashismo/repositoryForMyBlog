package com.ashish.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = {"com.ashish.repositories"})
@ComponentScan(basePackages = "com.ashish")
public class TestMongoDBConfig extends AbstractMongoConfiguration {

	@Override
	    protected String getDatabaseName() {
	        return "retail";
	    }

	    @Override
	    @Bean
	    public MongoClient mongo() throws Exception {
	        MongoClient client = new MongoClient("localhost", 27017);
//	        client.setWriteConcern(WriteConcern.SAFE);
	        return client;
	    }

	    @Override
	    protected String getMappingBasePackage() {
	        return "com.ashish.entities";
	    }
	
	    @Bean
	    public MongoTemplate mongoTemplate() throws Exception {
	        return new MongoTemplate(mongo(), getDatabaseName());
	    }

}
