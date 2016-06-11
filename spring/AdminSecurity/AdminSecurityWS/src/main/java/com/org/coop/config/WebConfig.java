package com.org.coop.config;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.org.coop.bs.config.BusinessServiceConfig;
import com.org.coop.society.data.transaction.config.DataAppConfig;

@Configuration 
@ComponentScan(basePackages = "com.org.coop") 
@Import({DataAppConfig.class, BusinessServiceConfig.class})
@EnableWebMvc   
//@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableSwagger2
public class WebConfig extends WebMvcConfigurerAdapter{
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
 
        return viewResolver;
    }

	@Bean
	public LoggingAspect getLoggingAspect() {
		return new LoggingAspect();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("js/");
		registry.addResourceHandler("/cactus/**").addResourceLocations("cactus/");
	}
	
	/**
	 * Injection of Jackson json converter as a third party service
	 */
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    	builder.serializationInclusion(Include.NON_NULL);
    	builder.serializationInclusion(Include.NON_EMPTY);
    	builder.indentOutput(true).dateFormat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));
    	converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    	converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
    }
}
