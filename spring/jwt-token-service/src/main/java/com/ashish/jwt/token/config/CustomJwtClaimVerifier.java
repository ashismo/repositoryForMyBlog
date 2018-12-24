package com.ashish.jwt.token.config;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;

public class CustomJwtClaimVerifier implements JwtClaimsSetVerifier {

	@Value("${security.jwt.issuer}")
	private String issuer;
	
	@Value("${security.jwt.client-id.to.be.matched}")
	private String clientIdentifiers;
	
	@Override
	public void verify(Map<String, Object> customJwtClaim) throws InvalidTokenException {
		String iss = (String) customJwtClaim.get("iss");
		if(iss != null && !issuer.equalsIgnoreCase(iss)) {
			throw new InvalidTokenException("Invalid issue: " + iss);
		}
		
		String clientIdentifier = (String) customJwtClaim.get("client_id");
		List<String> clientIdList = null;
		if(clientIdentifiers != null) {
			clientIdList = Arrays.asList(clientIdentifiers.split("\\s*,\\s*"));
		}
		if(clientIdentifier != null 
				&& clientIdList != null 
				&& clientIdList.size() > 0
				&& clientIdList.get(0) != null
				&& clientIdList.get(0).trim().length() > 0
				&& !clientIdList.contains(clientIdentifier)) {
			throw new InvalidTokenException("Invalid client identifier: " + clientIdentifier);
		}
	}

}
