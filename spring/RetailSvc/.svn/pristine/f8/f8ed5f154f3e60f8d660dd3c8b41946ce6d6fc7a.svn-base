package com.org.test.coop.retail.transaction.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.org.coop.retail.transaction.config.CustomerRoutingDataSource;

@Configuration
@EnableJpaRepositories(basePackages = {"com.org.coop.retail.repositories"}, 
					entityManagerFactoryRef="retailEntityManagerFactory", 
					transactionManagerRef="retailTransactionManager")
@EnableTransactionManagement
@PropertySource("classpath:retailApplicationData.properties")
public class RetailTestDBConfig {
	@Autowired
	private Environment env;

	@Bean(name = "customer1DataSource")
	@Qualifier("customer1DataSource")
	public DataSource customer1DataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.customer1.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}

	@Bean(name = "customer2DataSource")
	@Qualifier("customer2DataSource")
	public DataSource customer2DataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.customer2.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}
	
	/**
	 * This method selects database dynamically at runtime
	 * @return
	 */
	@Bean(name = "dataSource")
	@Qualifier("dataSource")
	public CustomerRoutingDataSource dataSource() {
		CustomerRoutingDataSource customerRoutingDataSource = new CustomerRoutingDataSource();
		customerRoutingDataSource.setDefaultTargetDataSource(customer1DataSource());
		Map<Object, Object> dsMap = new HashMap<Object, Object>();
		dsMap.put("retail", customer1DataSource());
		dsMap.put("retail1", customer2DataSource());
		
		customerRoutingDataSource.setTargetDataSources(dsMap);
		return customerRoutingDataSource;
	}
	
	@Bean(name = "retailEntityManagerFactory")
	@Qualifier("retailEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean retailEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.org.coop.retail.entities" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}
	
	@Bean(name="retailTransactionManager")
	@Qualifier("retailTransactionManager")
	public PlatformTransactionManager retailTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(retailEntityManagerFactory().getObject());
		return transactionManager;
	}
	

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
//		properties.setProperty("hibernate.use_sql_comments", "true");
		return properties;
	}
}
