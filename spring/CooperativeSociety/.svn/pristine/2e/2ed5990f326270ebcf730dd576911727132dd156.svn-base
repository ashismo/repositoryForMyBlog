package com.org.coop.society.data.transaction.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AdviceMode;
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

@Configuration
@EnableJpaRepositories(basePackages = {"com.org.coop.society.data.customer.repositories"}, 
					entityManagerFactoryRef="customerEntityManagerFactory", 
					transactionManagerRef="customerTransactionManager")
@EnableTransactionManagement(mode=AdviceMode.PROXY)
@PropertySource("classpath:application.properties")
public class CoOperativeCustomerDBConfig {
	@Autowired
	private Environment env;

	@Bean(name = "customerDataSource")
	@Qualifier("customerDataSource")
	public DataSource customerDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.customer.url"));
		dataSource.setUsername(env.getProperty("db.customer.username"));
		dataSource.setPassword(env.getProperty("db.customer.password"));
		return dataSource;
	}

	@Bean(name = "customerEntityManagerFactory")
	@Qualifier("customerEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(customerDataSource());
		em.setPackagesToScan(new String[] { "com.org.coop.society.data.customer" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}
	

	@Bean(name="customerTransactionManager")
	@Qualifier("customerTransactionManager")
	public PlatformTransactionManager customerTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(customerEntityManagerFactory().getObject());

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
		return properties;
	}
}
