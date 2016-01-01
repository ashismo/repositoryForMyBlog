package com.org.coop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.org.coop.bs.config.BusinessServiceConfig;
import com.org.coop.bs.config.DozerConfig;
import com.org.coop.society.data.transaction.config.DataAppConfig;

@Configuration 
@ComponentScan(basePackages = "com.org.coop") 
@Import({DataAppConfig.class, BusinessServiceConfig.class, DozerConfig.class})
@EnableWebMvc   
//@EnableAutoConfiguration
@EnableAspectJAutoProxy
public class WebConfig extends WebMvcConfigurerAdapter{
	@Bean
	public LoggingAspect getLoggingAspect() {
		return new LoggingAspect();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("js/");
	}
}
