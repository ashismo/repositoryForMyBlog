package com.ashish.spring.cloud.config;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudConfig extends AbstractCloudConfig {
	@Bean
	public DataSource  documentMongoDbFactory() {
	      return connectionFactory().dataSource("mySqlTest");
	}
}
