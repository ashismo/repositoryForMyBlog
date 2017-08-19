package com.ashish.learning.v4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ashish.learning.v4.HelloWorld;

@Configuration
@ComponentScan(basePackages="com.ashish.learning.v4")
@PropertySource({"classpath:app/properties/database.properties"}) // This annotation loads properties file and inject values
public class AppConfig {

	@Autowired
	private Environment env;
	
	// Below is the way to inject same class multiple times with different identifier.
	// We can not inject multiple bean with @Component annotation
	@Bean
    public HelloWorld helloWorld1() {
        return new HelloWorld();
    }
	
	@Bean
	@Scope(value="prototype")
    public HelloWorld helloWorld2() {
        return new HelloWorld();
    }
	
	@Bean(initMethod="init", destroyMethod="destroy")
    public HelloWorld helloWorldInitDestroy() {
        return new HelloWorld();
    }
	
	@Bean
	public DriverManagerDataSource datasource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		// If property is not found then the default value would get injected
		datasource.setUrl(env.getProperty("jdbc.url", "jdbc:mysql://localhost:3306/defaultdb"));
		datasource.setUsername(env.getProperty("jdbc.username", "defaultuser"));
		datasource.setPassword(env.getProperty("jdbc.password", "defaultpassword"));
		return datasource;
	}
}