package com.org.coop.service;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	public boolean validateLogin() {
		System.out.println("Loggedin");
		return true;
	}
}
