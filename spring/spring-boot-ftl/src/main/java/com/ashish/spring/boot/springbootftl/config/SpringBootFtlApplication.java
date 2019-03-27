package com.ashish.spring.boot.springbootftl.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.ashish.spring.boot")
public class SpringBootFtlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFtlApplication.class, args);
	}

}
