package com.ashish.apache.camel.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AController {
	
	@GetMapping("/health")
	public String health() {
		return "ok";
	}
}
