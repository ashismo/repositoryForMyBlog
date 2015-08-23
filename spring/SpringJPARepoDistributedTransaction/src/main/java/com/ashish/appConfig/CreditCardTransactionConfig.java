package com.ashish.appConfig;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
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
@EnableJpaRepositories(basePackages = {"com.ashish.credit.repositories"}, 
					entityManagerFactoryRef="creditEntityManagerFactory", 
					transactionManagerRef="creditTransactionManager")
@EnableTransactionManagement(mode=AdviceMode.PROXY)
@PropertySource("classpath:application.properties")
public class CreditCardTransactionConfig {
	@Autowired
	private Environment env;

	@Bean(name = "creditDataSource")
	@Qualifier("creditDataSource")
//	@Primary
	public DataSource creditDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.credit.url"));
		dataSource.setUsername(env.getProperty("db.credit.username"));
		dataSource.setPassword(env.getProperty("db.credit.password"));
		return dataSource;
	}

	@Bean(name = "creditEntityManagerFactory")
	@Qualifier("creditEntityManagerFactory")
//	@Primary
	public LocalContainerEntityManagerFactoryBean creditEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(creditDataSource());
		em.setPackagesToScan(new String[] { "com.ashish.credit" });
		em.setPersistenceUnitName("creditPersistenceUnit");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}
	

	@Bean(name="creditTransactionManager")
	@Qualifier("creditTransactionManager")
//	@Primary
	public PlatformTransactionManager creditTransactionManager(
			EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

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
