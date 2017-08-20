package com.ashish.learning.v4.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.ashish.learning.v4.HelloWorld;

@Configuration
@ComponentScan(basePackages = "com.ashish.learning.v4")
@PropertySource({ "classpath:app/properties/database.properties" })
// This annotation loads properties file and inject values
@Import({ DevDBConfig.class, TestDBConfig.class })
@EnableCaching // This enables caching in spring application
public class AppConfig {

	@Autowired
	private Environment env;

	// Below is the way to inject same class multiple times with different
	// identifier.
	// We can not inject multiple bean with @Component annotation
	@Bean
	public HelloWorld helloWorld1() {
		return new HelloWorld();
	}

	@Bean
	@Scope(value = "prototype")
	public HelloWorld helloWorld2() {
		return new HelloWorld();
	}

	@Bean(initMethod = "init", destroyMethod = "destroy")
	public HelloWorld helloWorldInitDestroy() {
		return new HelloWorld();
	}
	
	/**
	 * This map bean would be injected into another class
	 * @return
	 */
	@Bean
	public Map<String, String> countryCurrencyMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("USA", "USD");
		map.put("India", "INR");
		map.put("UAE", "AED");
		return map;
	}
	
	/**
	 * This list bean would be injected into another class
	 * @return
	 */
	@Bean
	public List<String> countryList() {
		List<String> list = new ArrayList<String>();
		list.add("India");
		list.add("USA");
		list.add("UAE");
		return list;
	}
	
	/**
	 * This bean loads i18n messages from the locale based properties files
	 * @return
	 */
	@Bean
    public MessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/message", "messages/errormessage");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

	/**
	 * Setter injection: At a given point of time, only one datasource would be active (as they are profile based) and would be injected here
	 * @param datasource
	 * @return
	 */
	@Bean
	@Autowired
	public DataSourceTransactionManager transactionManager(DataSource datasource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(datasource);

		return transactionManager;
	}
	
	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource datasource) {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(datasource);
		return jdbcTemplate;
	}
	
	
//	@Bean
//	public CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager();
//	}
	
	/**
	 * Cache manager bean configuration
	 * @return
	 */
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}
	
	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));// EH-cache is configured in the xml file
		cmfb.setShared(true);
		return cmfb;
	}
}