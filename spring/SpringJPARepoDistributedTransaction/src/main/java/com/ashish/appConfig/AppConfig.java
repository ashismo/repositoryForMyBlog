package com.ashish.appConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.ashish")
@PropertySource("classpath:application.properties")
@Import({CreditCardTransactionConfig.class, DebitCardTransactionConfig.class})
public class AppConfig {
	
	@Autowired
	private Environment env;

}
