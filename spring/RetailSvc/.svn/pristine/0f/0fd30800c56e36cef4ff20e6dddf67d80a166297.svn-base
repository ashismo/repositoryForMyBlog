package com.org.test.coop.society.data.transaction.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.org.coop")
@EnableJpaRepositories(basePackages = {"com.org.coop.retail.repositories"}, 
					entityManagerFactoryRef="retailEntityManagerFactory", 
					transactionManagerRef="retailTransactionManager")
@EnableTransactionManagement
//@PersistenceContext(type = PersistenceContextType.EXTENDED) // This configuration solves “failed to lazily initialize a collection of role” problem i.e. EAGER load
@PropertySource("classpath:retailSvcWSTest.properties")
public class TestRetailDBConfig {
	@Autowired
	private Environment env;
	
	@Value("classpath:truncate_db_proc.sql")
	private Resource schemaScript;

	@Bean(name = "retailDataSource")
	@Qualifier("retailDataSource")
	public DataSource retailDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.test.driver"));
		dataSource.setUrl(env.getProperty("db.test.retail.url"));
		dataSource.setUsername(env.getProperty("db.test.admin.username"));
		dataSource.setPassword(env.getProperty("db.test.admin.password"));
		if("true".equalsIgnoreCase(env.getProperty("clean.db"))) {
			DatabasePopulatorUtils.execute(getDatabasePopulator(), dataSource);
		}
		return dataSource;
	}
	
	private DatabasePopulator getDatabasePopulator() {
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.addScript(schemaScript);
//		rdp.addScript(dataScript);
		//rdp.setContinueOnError(true);
		return rdp;
	}
	
	@Bean(name = "retailEntityManagerFactory")
	@Qualifier("retailEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean retailEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(retailDataSource());
		em.setPackagesToScan(new String[] { "com.org.coop.retail.entities" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		//em.setJpaProperties(additionalProperties());

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
//		properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		
//		properties.setProperty("hibernate.use_sql_comments", "true");
		return properties;
	}
}
