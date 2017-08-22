package com.ashish.learning.v4.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import com.ashish.learning.v4.duplicate.beans.DuplicateBean;

@Component
@PropertySource({"classpath:app/properties/database.properties"}) 
public class DevDBConfig {
	@Autowired
	private Environment env;
	
	@Bean
	@Profile("dev")
	public DriverManagerDataSource dataSourceH2db() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName(env.getProperty("h2db.driverClassName"));
		datasource.setUrl(env.getProperty("h2db.inmemory.url"));
		datasource.setUsername(env.getProperty("h2db.username"));
		datasource.setPassword(env.getProperty("h2db.password"));
		
		// Initialize the database
		DatabasePopulatorUtils.execute(createAllTables(), datasource);
		return datasource;
	}
	
	private DatabasePopulator createAllTables() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        
        databasePopulator.addScript(new ClassPathResource("create-db.sql"));
        return databasePopulator;
    }
	
	/**
	 * Integrated embedded H2 database
	 * @return
	 */
	@Bean
	@Profile("devEmbedded")
	public DataSource dataSourceEmbeddedDb() {
		EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
		dbBuilder.setType(EmbeddedDatabaseType.H2);
		dbBuilder.addScript("classpath:create-db.sql");
		return dbBuilder.build();
	}

	@Bean
	public DuplicateBean dulicateBean() {
		DuplicateBean duplicateBean = new DuplicateBean();
		duplicateBean.setName("Duplicate1");
		return duplicateBean;
	}
}
