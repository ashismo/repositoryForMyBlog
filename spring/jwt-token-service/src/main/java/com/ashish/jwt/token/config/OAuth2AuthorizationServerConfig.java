package com.ashish.jwt.token.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${security.jwt.client-id}")
	private String clientIds;
	
	@Value("${security.jwt.client-secret}")
	private String clientSecrets;
	
	@Value("${security.jwt.grant-type}")
	private String grantType;
	
	@Value("${security.jwt.scope-read}")
	private String scopeRead;
	
	@Value("${security.jwt.scope-write}")
	private String scopeWrite;
	
	@Value("${security.jwt.access.token.validity}")
	private Integer accessTokValidityInSec;
	
	@Value("${security.jwt.issuer}")
	private String issuer;
	
	@Value("${security.jwt.audience}")
	private String audience;
	
	@Autowired	
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
    	if(clientIds != null) {
    		List<String> clientIdList = Arrays.asList(clientIds.split("\\s*,\\s*"));
    		List<String> clientSecretList = Arrays.asList(clientSecrets.split("\\s*,\\s*"));
    		int i = 0;
    		InMemoryClientDetailsServiceBuilder inMem = configurer.inMemory();
    		for(String clientId: clientIdList) {
    			inMem
    				.withClient(clientId)
    				.secret(passwordEncoder.encode(clientSecretList.get(i++)))
    				.authorizedGrantTypes("auhorization_code", "refresh_token", "password", "client_credentials")
    				.scopes(scopeRead, scopeWrite)
    				.resourceIds(audience)
    				.accessTokenValiditySeconds(accessTokValidityInSec);
    		}
    	
    	}
    }
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		TokenEnhancerChain tokenEnhancer = new TokenEnhancerChain();
		tokenEnhancer.setTokenEnhancers(Arrays.asList(accessTokenConverter));
		
		endpoints.tokenStore(tokenStore)
			.accessTokenConverter(accessTokenConverter)
			.tokenEnhancer(tokenEnhancer)
			.authenticationManager(authenticationManager);
		
	}

}
