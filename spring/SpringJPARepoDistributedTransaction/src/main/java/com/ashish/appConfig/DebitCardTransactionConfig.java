package com.ashish.appConfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackages = {"com.ashish.debit.repositories"}, 
					entityManagerFactoryRef="debitEntityManagerFactory", 
					transactionManagerRef="debitTransactionManager")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DebitCardTransactionConfig {
	@Autowired
	private Environment env;

	@Bean(name = "debitDataSource")
	@Qualifier("debitDataSource")
	@Primary
	public DataSource debitDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.debit.url"));
		dataSource.setUsername(env.getProperty("db.debit.username"));
		dataSource.setPassword(env.getProperty("db.debit.password"));
		return dataSource;
	}

	@Bean(name = "debitEntityManagerFactory")
	@Qualifier("debitEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean debitEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(debitDataSource());
		em.setPackagesToScan(new String[] { "com.ashish.debit" });
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}
	

	@Bean(name="debitTransactionManager")
	@Qualifier("debitTransactionManager")
	@Primary
	public PlatformTransactionManager debitTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(debitEntityManagerFactory().getObject());

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
