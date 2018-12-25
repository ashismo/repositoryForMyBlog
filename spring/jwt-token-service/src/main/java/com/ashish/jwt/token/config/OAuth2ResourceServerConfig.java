package com.ashish.jwt.token.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${security.jwt.audience}")
	private String audience;
	
	@Autowired
	private ResourceServerTokenServices resourceServerTokenServices;
		
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(audience).tokenServices(resourceServerTokenServices);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.requestMatchers()
			.and()
			.authorizeRequests()
			.antMatchers("/get/**", "/swagger-ui.html/**", "/v2/api-docs/**", "/console/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.csrf().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
			//below line is to make http://localhost:8080/console/ enable
			http.headers().frameOptions().disable();
			
			http.formLogin().disable();
	}
	
}