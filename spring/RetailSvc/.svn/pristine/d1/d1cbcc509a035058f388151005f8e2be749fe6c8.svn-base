package com.org.coop.retail.transaction.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.org.coop.society.data.transaction.config.CoOperativeAdminDBConfig;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.org.coop")
@PropertySource("classpath:retailApplicationData.properties")
@Import({RetailDBConfig.class, CoOperativeAdminDBConfig.class})
public class CustomerDataAppConfig {
	
	@Autowired
	private Environment env;

}
