package com.ashish.spring.cloud.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile("local")
public class LocalConfig implements Config {
	@Bean(name="dataSource")
	public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	      dataSource.setUrl("jdbc:mysql://localhost:3306/test");
	      dataSource.setUsername( "root");
	      dataSource.setPassword( "root" );
	      return dataSource;
	   }
	
}
