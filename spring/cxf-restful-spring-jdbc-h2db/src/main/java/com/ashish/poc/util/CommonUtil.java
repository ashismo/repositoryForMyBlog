package com.ashish.poc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class CommonUtil {

	@Autowired
	private Environment env;
	
	public void backupCurrentDatabaseState() {
		try {
			String args[] = {
					"-url",
					env.getProperty("h2db.file.url"),
					"-user", "sa", "-password", "" };
			org.h2.tools.Script.main(args);
			System.out.println("Database backup executed...");
		} catch(Exception e) {
			System.out.println("Batabase backup failed.");
			e.printStackTrace();
		}
	}
}
