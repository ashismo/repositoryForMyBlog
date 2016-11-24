package com.ashish.poc.security.config;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ashish.poc.model.UserProfile;
import com.ashish.poc.security.config.exception.HttpUnauthorizedException;
import com.ashish.poc.services.UserServices;
import com.ashish.poc.util.PasswordEncodeDecodeUtil;
import com.ashish.poc.util.WebConstants;

@Component
@PropertySource("classpath:config.properties")
public class RestEndpointSecurityInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger.getLogger(RestEndpointSecurityInterceptor.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserServices userService;
	
	@Autowired
	private PasswordEncodeDecodeUtil passwordEncodeDecodeUtil;
	
	public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("Request URL::" + request.getRequestURL().toString()
                + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        if(request.getRequestURL().toString().contains("/rest/")) {
        	UserProfile userProfile = (UserProfile)request.getSession().getAttribute(WebConstants.SV_USER_PROFILE);
        	
        	if(userProfile != null && userProfile.isUserAuthenticated()) {
        		log.info("User has to be redirected to the requested resource if authorized");

        		String isHttpBasicAuthenticationEnabled = env.getProperty("enable.http.basic.authentication");
        		if("true".equalsIgnoreCase(isHttpBasicAuthenticationEnabled)) {
	        		String authCredentials = request.getHeader(WebConstants.AUTHENTICATION_HEADER);
	        		try {
		        		final String encodedUserPassword = authCredentials.replaceFirst("Basic ", "");
		        		String usernameAndPassword = null;
	        		
	        			byte[] decodedBytes = passwordEncodeDecodeUtil.base64decode(encodedUserPassword).getBytes();
	        			usernameAndPassword = new String(decodedBytes, "UTF-8");
	        		
		        		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		        		final String username = tokenizer.nextToken();
		        		final String password = tokenizer.nextToken();
		        		if(!username.equals(userProfile.getUserName()) || !userService.isUserAuthenticated(username, password)) {
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
