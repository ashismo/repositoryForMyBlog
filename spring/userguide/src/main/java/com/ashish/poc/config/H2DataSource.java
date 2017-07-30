package com.ashish.poc.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ashish.poc.security.config.SecurityConfig;

//@Profile("h2")
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
@Import(SecurityConfig.class)
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
			
			if("true".equalsIgnoreCase(env.getProperty("h2db.prod.environment"))) {  // Production environment is unix server
				dmDataSource.setUrl(env.getProperty("h2db.file.url.unix.path"));
			} else {
				dmDataSource.setUrl(env.getProperty("h2db.file.url"));
			}
			
			
			if(!"true".equalsIgnoreCase(env.getProperty("h2db.prod.environment")) &&
					"true".equalsIgnoreCase(env.getProperty("h2db.file.loadFromFile"))) {
				if("true".equalsIgnoreCase(env.getProperty("h2db.file.dropAllTables"))) {
					DatabasePopulatorUtils.execute(dropAllTables(), dmDataSource);
				}
				DatabasePopulatorUtils.execute(createAllTables(), dmDataSource);
				if("true".equalsIgnoreCase(env.getProperty("h2db.file.dropAllTables"))) { // If tables are not dropped then there is no point in inserting data again
					DatabasePopulatorUtils.execute(populateDatabase(), dmDataSource);
				}
			}else if("true".equalsIgnoreCase(env.getProperty("h2db.prod.environment"))) { // For production environment
				DatabasePopulatorUtils.execute(createAllTables(), dmDataSource);
			}
			
		} else {
		
		/**
		 *  Below commented line loads database with the scripts used in createDatabasePopulator() function.
		 *  Once loaded then it uses in-memory database
		 */
			dmDataSource.setUrl(env.getProperty("h2db.inmemory.url"));
			DatabasePopulatorUtils.execute(createAllTables(), dmDataSource);
			DatabasePopulatorUtils.execute(populateDatabase(), dmDataSource);
		}
		return dmDataSource;

	}
	
	private DatabasePopulator populateDatabase() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        
        databasePopulator.addScript(new ClassPathResource("db/sql/insert-data.sql"));
        return databasePopulator;
    } 
	
	private DatabasePopulator dropAllTables() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        
        databasePopulator.addScript(new ClassPathResource("db/sql/drop-all-tables.sql"));
        return databasePopulator;
    } 
	
	private DatabasePopulator createAllTables() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        
        databasePopulator.addScript(new ClassPathResource("db/sql/create-db.sql"));
        return databasePopulator;
    }

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		System.out.println("Initialize jdbcTemplate");
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
		return jdbcTemplate;

	}
	
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}
	
	// Start WebServer, access http://localhost:8082
//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public Server startDBManager() throws SQLException {
//		return Server.createWebServer();
//	}
}
