package com.ashish.spring.cloud.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(basePackages="com.ashish.debit.repositories", transactionManagerRef="debitTransactionManager")
@EnableTransactionManagement
@ComponentScan(basePackages="com.ashish")
public class CloudDBConfig {
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
//	@Bean(name="dataSource1")
//	public DataSource dataSource(){
//	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	      dataSource.setUrl("jdbc:mysql://us-cdbr-iron-east-04.cleardb.net:3306/ad_45db5c38c157ae9");
//	      dataSource.setUsername( "badcfc3604e2e8");
//	      dataSource.setPassword( "1aad4ceb" );
//	      return dataSource;
//	   }
	
	@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource);
	      em.setPackagesToScan(new String[] { "com.ashish.debit" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	 
	      return em;
	   }
	 
	 @Bean(name="debitTransactionManager")
	   public PlatformTransactionManager transactionManager(){
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
	 
	      return transactionManager;
	   }
	 
	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	      return new PersistenceExceptionTranslationPostProcessor();
	   }
	 
	   Properties additionalProperties() {
	      Properties properties = new Properties();
	      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	      properties.setProperty("hibernate.show_sql", "true");
	      return properties;
	   }
}
