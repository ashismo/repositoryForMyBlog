package com.ashish.spring.cloud.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
public class CloudConfig extends AbstractCloudConfig {
	@Bean(name="dataSource")
	@Primary
	public DataSource  dataSource() {
	      return connectionFactory().dataSource("mySqlTest");
	}
	
	
//	@Bean(name="dataSource")
//	@Primary
//	public DataSource dataSource(){
//	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	      dataSource.setUrl("jdbc:mysql://us-cdbr-iron-east-04.cleardb.net:3306/ad_45db5c38c157ae9");
//	      dataSource.setUsername( "badcfc3604e2e8");
//	      dataSource.setPassword( "1aad4ceb" );
//	      return dataSource;
//	   }
	
}
