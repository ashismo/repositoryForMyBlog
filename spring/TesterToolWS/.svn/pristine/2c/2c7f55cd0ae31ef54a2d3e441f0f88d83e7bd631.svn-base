package com.org.coop.config;

import java.util.Arrays;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.org.coop.controller.JerseyController;

@Configuration 
@ComponentScan(basePackages = "com.org.coop") 
public class CXFWebConfig {
	@ApplicationPath("/rest1")
    public class JaxRsApiApplication extends Application { }
 
    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }
 
    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer(ApplicationContext appContext) {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(jaxRsApiApplication(), JAXRSServerFactoryBean.class);
        factory.setServiceBeans(Arrays.<Object>asList(jerseyController(), exceptionResource()));
        factory.setAddress("/" + factory.getAddress());
        factory.setProvider(jsonProvider());
        return factory.create();
    }
 
    @Bean
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }
 
    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }
    
    @Bean
    public JerseyController jerseyController() {
        return new JerseyController();
    }
    @Bean
    public ExceptionResource exceptionResource() {
        return new ExceptionResource();
    }
}
