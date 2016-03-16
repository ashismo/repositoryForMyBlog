package com.ashish.config;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SpringBootApplication(scanBasePackages="com.ashish")
public class WebConfig extends SpringBootServletInitializer {

	/**
	 * This method configures the web application
	 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebConfig.class);
    }

    /**
     * Run the spring boot application in embedded tomcat 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebConfig.class, args);
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