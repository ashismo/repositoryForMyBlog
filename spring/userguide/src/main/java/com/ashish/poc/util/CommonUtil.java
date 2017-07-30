package com.ashish.poc.util;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class CommonUtil {

	private static final Logger log = Logger.getLogger(CommonUtil.class);
	
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
	
	public Timestamp getCurrentTimeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public String getLoggedInUsername() {
		String username = null;
		
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			username = user.getUsername();
		} catch (Exception e) {
			log.error("Loggedin user name not found: ", e);
			username = null;
		}
		
        
        return username;
	}
}
