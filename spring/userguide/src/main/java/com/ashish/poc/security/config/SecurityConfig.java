package com.ashish.poc.security.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity(debug=false)

// Below annotation is required to use @PreAuthorize
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger log = Logger.getLogger(SecurityConfig.class);

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService customUserDetailsService;
	
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
		 http.authorizeRequests()
		 	.antMatchers("/login*").permitAll()
		 	.antMatchers("/static/**").permitAll()
		 	.antMatchers("/rest/svc/guide/get*").permitAll();
	        //.and().exceptionHandling().accessDeniedPage("/rest/svc/guide/accessDenied");
		 
		 http.csrf().disable();
		 http.formLogin().loginPage("/login.jsp").failureUrl("/login.jsp?error=true")
		 .loginProcessingUrl("/j_spring_security_check")
		 .defaultSuccessUrl("/dashboard.jsp")
		 .permitAll().and()
         .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login.jsp")                                    
         .permitAll();
		 
		 //http.logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login.jsp");
		 
	}
}