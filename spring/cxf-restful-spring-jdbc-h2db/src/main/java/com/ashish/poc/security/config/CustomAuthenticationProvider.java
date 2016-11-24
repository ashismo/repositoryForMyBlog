package com.ashish.poc.security.config;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component("authenticationProvider")
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
	private static final Logger log = Logger.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	public CustomAuthenticationProvider(UserDetailsService customUserDetailsService) {
		this.setUserDetailsService(customUserDetailsService);
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		// Could assert pre-conditions here, e.g. rate-limiting
		// and throw a custom AuthenticationException if necessary
		try {
			
			Authentication auth = super.authenticate(authentication);
			String userName = authentication.getName();
			return auth;
		} catch (BadCredentialsException e) {
			// Will throw a custom exception if too many failed logins have
			// occurred
			log.error("Bad credential: ", e);
			System.out.println("Bad credential: " + authentication.getName());
			throw e;
		}
	}
}
