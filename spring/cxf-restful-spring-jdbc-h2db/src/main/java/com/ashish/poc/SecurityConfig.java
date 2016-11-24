package com.ashish.poc.security.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug=true)
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger log = Logger.getLogger(SecurityConfig.class);

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService customUserDetailsService;
	
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//	  auth.inMemoryAuthentication().withUser("xxx").password("123456").roles("USER");
//	  auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
//	  auth.inMemoryAuthentication().withUser("user").password("123456").roles("DBA");
		try {
    		auth.userDetailsService(customUserDetailsService);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

//	  http.authorizeRequests()
//		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//		.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
//		.and().formLogin();
		
		 http.authorizeRequests()
	        .antMatchers("/", "/home","/rest/login", "/rest/svc/guide/admin/createUser").permitAll()
	        //.and().formLogin().loginPage("/login")
	        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
		 //http.addFilterBefore(basicAuthenticationFilter(), BasicAuthenticationFilter.class);
	  http.authenticationProvider(authenticationProvider);

	}
	
	@Bean
	public BasicAuthenticationEntryPoint authenticationEntryPoint() {
		BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new BasicAuthenticationEntryPoint();
		basicAuthenticationEntryPoint.setRealmName("Authorization:");
		return basicAuthenticationEntryPoint;
	}
	
	@Bean
	public BasicAuthenticationFilter basicAuthenticationFilter() {
		BasicAuthenticationFilter basicAuthenticationEntryPoint = null;
		try {
			basicAuthenticationEntryPoint = new BasicAuthenticationFilter(authenticationManager(),authenticationEntryPoint());
		} catch (Exception e) {
			log.error("Error is basic authentication filter", e);
		}
		return basicAuthenticationEntryPoint;
	}
}
