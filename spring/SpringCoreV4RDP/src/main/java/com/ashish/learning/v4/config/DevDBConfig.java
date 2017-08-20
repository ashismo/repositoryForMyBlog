package com.ashish.learning.v4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

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
		return datasource;
	}

}
