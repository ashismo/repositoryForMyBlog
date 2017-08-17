package com.ashish.learning.v4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.ashish.learning.v4.BMWCar;
import com.ashish.learning.v4.FourWheelers;
import com.ashish.learning.v4.HelloWorld;
import com.ashish.learning.v4.ShowRoom;

@Configuration
@ComponentScan(basePackages="com.ashish.learning.v4")
public class AppConfig {

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
}
