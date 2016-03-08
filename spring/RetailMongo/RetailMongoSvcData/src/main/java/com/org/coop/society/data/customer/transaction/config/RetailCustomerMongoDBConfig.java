package com.org.coop.society.data.customer.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;


@Configuration
@EnableMongoRepositories(basePackages = {"com.org.coop.retail.data.repositories"})
@ComponentScan(basePackages = "com.org.coop")
@PropertySource("classpath:retailApplicationData.properties")
public class RetailCustomerMongoDBConfig extends AbstractMongoConfiguration {

	@Autowired
	private Environment env;
	
	@Override
	    protected String getDatabaseName() {
	        return env.getProperty("mongodb.name");
	    }

	    @Override
	    @Bean
	    public MongoClient mongo() throws Exception {
	        MongoClient client = new MongoClient(env.getProperty("mongodb.host"), Integer.valueOf(env.getProperty("mongodb.port")));
//	        client.setWriteConcern(WriteConcern.SAFE);
	        return client;
	    }

	    @Override
	    protected String getMappingBasePackage() {
	        return "com.org.coop.retail.data.entities";
	    }
	
	    @Bean
	    public MongoTemplate mongoTemplate() throws Exception {
	        return new MongoTemplate(mongo(), getDatabaseName());
	    }

}
