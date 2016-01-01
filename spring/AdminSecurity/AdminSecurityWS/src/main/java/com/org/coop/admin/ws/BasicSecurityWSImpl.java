package com.org.coop.admin.ws;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Encoder;

@Component
@PropertySource("classpath:applicationWS.properties")
public class BasicSecurityWSImpl {
	@Autowired
	protected Environment env;
	
	protected HttpEntity<String> getHttpEntities() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		String username = env.getProperty("basic.auth.username");
        String password = env.getProperty("basic.auth.password");
        String authString = username + ":" + password;
        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
		headers.add("Authorization", "Basic " + authStringEnc);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return entity;
	}
}
