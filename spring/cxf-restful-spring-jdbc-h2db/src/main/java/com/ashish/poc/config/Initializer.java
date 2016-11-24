package com.ashish.poc.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class Initializer {
	
	@Autowired
	private Environment env;
	
	@PostConstruct
	public void init() {
		System.out.println("Initializing h2sql manager");
		try {
			
			Class.forName(env.getProperty("h2db.driver"));
			
			String inMemoryInd = env.getProperty("h2db.inmemory.active");
			
			/**
			 * This code opens HSQL database manager for H2 database 
			 */
			
			if("true".equalsIgnoreCase(env.getProperty("h2db.display.hsql.manager"))) {
				if(!"true".equalsIgnoreCase(inMemoryInd)) {
					org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url",
							env.getProperty("h2db.file.url"), "--noexit", "--user", "sa",
							"--password", "" });
				} else {
				
					org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url",
							env.getProperty("h2db.inmemory.url"), "--noexit", "--user", "sa",
							"--password", "" });
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
