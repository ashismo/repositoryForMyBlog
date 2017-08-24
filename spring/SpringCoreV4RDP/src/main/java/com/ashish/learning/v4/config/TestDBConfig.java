package com.ashish.learning.v4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.ashish.learning.v4.duplicate.beans.DuplicateBean;

@Configuration
@ComponentScan(basePackages = "com.ashish.learning.v4")
@PropertySource({ "classpath:app/properties/database.properties" })
public class TestDBConfig {

	@Autowired
	private Environment env;

	@Bean
	@Profile("test")
	public DriverManagerDataSource datasource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		// If property is not found then the default value would get injected
		datasource.setUrl(env.getProperty("jdbc.url",
				"jdbc:mysql://localhost:3306/defaultdb"));
		datasource.setUsername(env.getProperty("jdbc.username", "defaultuser"));
		datasource.setPassword(env.getProperty("jdbc.password",	"defaultpassword"));

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

	@Bean
	public DuplicateBean dulicateBean() {
		DuplicateBean duplicateBean = new DuplicateBean();
		duplicateBean.setName("Duplicate2");
		return duplicateBean;
	}
}
