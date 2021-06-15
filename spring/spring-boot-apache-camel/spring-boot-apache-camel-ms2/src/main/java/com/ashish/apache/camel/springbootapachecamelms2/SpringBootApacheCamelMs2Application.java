package com.ashish.apache.camel.springbootapachecamelms2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ashish.apache.camel.controllers")
public class SpringBootApacheCamelMs2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApacheCamelMs2Application.class, args);
	}

}
