package com.org.coop.society.data.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.org.coop")
@PropertySource("classpath:application.properties")
@Import({CoOperativeAdminDBConfig.class})
public class DataAppConfig {
	
	@Autowired
	private Environment env;

}
