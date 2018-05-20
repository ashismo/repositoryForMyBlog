package com.inventory.mgmt.config;

import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SpringBootApplication(scanBasePackages="com.inventory.mgmt")
public class InventoryMgmtConfig {
	public static void main(String[] args) throws Exception {
        SpringApplication.run(InventoryMgmtConfig.class, args);
    }
	
	@Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
    	Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    	builder.serializationInclusion(Include.NON_NULL);
    	builder.serializationInclusion(Include.NON_EMPTY);
    	builder.indentOutput(true).dateFormat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));
    	return builder;
    }
}
