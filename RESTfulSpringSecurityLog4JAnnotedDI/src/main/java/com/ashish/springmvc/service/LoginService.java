package com.ashish.springmvc.service;

import org.springframework.stereotype.Service;

/**
 * This is service class. @Service informs IOC container about the dependency injection  
 * @author Ashish Mondal
 *
 */
@Service
public class LoginService implements LoginInterface {
	public boolean isLoginSuccess() {
		return true;
	}
}
