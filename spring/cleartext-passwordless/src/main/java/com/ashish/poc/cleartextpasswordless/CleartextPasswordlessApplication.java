package com.ashish.poc.cleartextpasswordless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class CleartextPasswordlessApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleartextPasswordlessApplication.class, args);
	}

}
