package com.ashish.poc.config;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.h2.tools.Server;
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
			
			try {
				Server server = Server.createTcpServer(new String[] {}).start();
				System.out.println("Ashish: TCP port: " + server.getPort());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/**
			 * This code opens HSQL database manager for H2 database 
			 */
			
			if("true".equalsIgnoreCase(env.getProperty("h2db.display.hsql.manager"))) {
				
				if(!"true".equalsIgnoreCase(inMemoryInd)) {
					if(!"true".equalsIgnoreCase(env.getProperty("h2db.prod.environment"))) {
						org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url",
							env.getProperty("h2db.file.url"), "--noexit", "--user", "sa",
							"--password", "" });
					}
				} else {
					if(!"true".equalsIgnoreCase(env.getProperty("h2db.prod.environment"))) {
						org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url",
							env.getProperty("h2db.inmemory.url"), "--noexit", "--user", "sa",
							"--password", "" });
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
