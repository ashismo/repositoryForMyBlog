/**
 * Every request will be intercepted at this class. preHandle() intercepts all requests before landing to the endpoint
 * And postHandle() handles POST Requests
 */
package com.org.coop.authentication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.coop.org.exception.HttpUnauthorizedException;
import com.org.coop.admin.service.AdminLoginService;
import com.org.coop.canonical.beans.UserProfile;
import com.org.coop.constants.WebConstants;

@Component
@PropertySource("classpath:application.properties")
public class RestEndpointSecurityInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger.getLogger(RestEndpointSecurityInterceptor.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private AdminLoginService adminLoginService;
	
	public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Request URL::" + request.getRequestURL().toString()
                + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        if(request.getRequestURL().toString().contains("/rest/")) {
        	UserProfile userProfile = (UserProfile)request.getSession().getAttribute(WebConstants.SV_USER_PROFILE);
        	
        	if(userProfile != null && userProfile.isUserReAuthenticated()) {
        		log.info("User has to be redirected to the requested resource if authorized");

        		String isHttpBasicAuthenticationEnabled = env.getProperty("enable.http.basic.authentication");
        		if("true".equalsIgnoreCase(isHttpBasicAuthenticationEnabled)) {
	        		String authCredentials = request.getHeader(WebConstants.AUTHENTICATION_HEADER);
	        		try {
		        		final String encodedUserPassword = authCredentials.replaceFirst("Basic ", "");
		        		String usernameAndPassword = null;
	        		
	        			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
	        			usernameAndPassword = new String(decodedBytes, "UTF-8");
	        		
		        		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		        		final String username = tokenizer.nextToken();
		        		final String password = tokenizer.nextToken();
		        		if(!username.equals(userProfile.getUserName()) || !adminLoginService.isUserAuthenticated(username, password)) {
		        			log.error("User is not authenticated: " + username);
		        			throw new HttpUnauthorizedException("User is not authenticated:" + username);
		        		}
	        		} catch (Exception e) {
	        			log.error("Exception occured while authenticating user: ", e);
	        			throw new HttpUnauthorizedException("Exception occured while authenticating user");
	        		}
        		}
        	} else {
        		request.getSession().invalidate();
        		log.info("User session destroyed. User is redircted into login page");
        		String redirect = request.getContextPath() + "/login?incompleteLogin";
        		response.sendRedirect(redirect);
        		return false;
        	}
        } 
        //if returned false, we need to make sure 'response' is sent
        return true;
    }
	
}
