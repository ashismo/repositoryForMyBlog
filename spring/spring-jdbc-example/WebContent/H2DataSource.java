package com.ashish.poc.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Profile("h2")
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
public class H2DataSource {
	// jdbc:h2:mem:testdb
	
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dmDataSource = new DriverManagerDataSource();
		dmDataSource.setDriverClassName(env.getProperty("h2db.driver"));
		dmDataSource.setUsername(env.getProperty("h2db.user"));
		dmDataSource.setPassword(env.getProperty("h2db.password"));
		/**
		 * Below line uses the testdb.h2.db file as database.
		 */
		
		String inMemoryInd = env.getProperty("h2db.inmemory.active");
		
		if(!"true".equalsIgnoreCase(inMemoryInd)) {
			dmDataSource.setUrl(env.getProperty("h2db.file.url"));
			if(!"true".equalsIgnoreCase(env.getProperty("h2db.prod.environment")) &&
					"true".equalsIgnoreCase(env.getProperty("h2db.file.loadFromFile"))) {
				DatabasePopulatorUtils.execute(createDatabasePopulator(), dmDataSource);
			}
			
		} else {
		
		/**
		 *  Below commented line loads database with the scripts used in createDatabasePopulator() function.
		 *  Once loaded then it uses in-memory database
		 */
			dmDataSource.setUrl(env.getProperty("h2db.inmemory.url"));
			DatabasePopulatorUtils.execute(createDatabasePopulator(), dmDataSource);
		}
		return dmDataSource;

	}
	
	private DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("db/sql/create-db.sql"));
        databasePopulator.addScript(new ClassPathResource("db/sql/insert-data.sql"));
        return databasePopulator;
    } 

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		System.out.println("Initialize jdbcTemplate");
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource());
		return jdbcTemplate;

	}
	
	
	@Bean(name="txManager")
	public DataSourceTransactionManager txManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}
	
//	@Bean
//    public PlatformTransactionManager txManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
//
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return txManager();
//    }
	
	// Start WebServer, access http://localhost:8082
//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public Server startDBManager() throws SQLException {
//		return Server.createWebServer();
//	}
}
