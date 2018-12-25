package com.ashish.jwt.token.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// This exclusion is needed to avoid auto generation of password with 
// "Using generated security password:" message in the logfile
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class },
			scanBasePackages={"com.ashish.jwt.token"})
public class JwtTokenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTokenServiceApplication.class, args);
	}

}

