package com.ashish.poc.cleartextpasswordless.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healthcheck {
	
	@Value("${db.password}")
	private String pwd;
	
	@GetMapping("/health")
	public String health() {
		
		return pwd;
	}
}
