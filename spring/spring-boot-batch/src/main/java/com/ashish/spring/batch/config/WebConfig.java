package com.ashish.spring.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages="com.ashish")
@EnableBatchProcessing
@EnableScheduling // Enables scheduling
public class WebConfig {


    /**
     * Run the spring boot application in embedded tomcat 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebConfig.class, args);
    }

}