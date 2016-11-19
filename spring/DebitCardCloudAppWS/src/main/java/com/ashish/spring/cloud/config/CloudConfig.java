package com.ashish.spring.cloud.config;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class CloudConfig extends AbstractCloudConfig implements Config {
	@Bean(name="dataSource")
	@Primary
	public DataSource  dataSource() {
	      return connectionFactory().dataSource("MySqlDBService");
	}
	
	
//	@Bean(name="dataSource")
//	@Primary
//	public DataSource dataSource(){
//	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	      dataSource.setUrl("jdbc:mysql://us-cdbr-iron-east-04.cleardb.net:3306/ad_45db5c38c157ae9");
//	      dataSource.setUsername( "badcfc3604e2e8");
//	      dataSource.setPassword( "1aad4ceb" );
//	      return dataSource;
//	   }
	
}
