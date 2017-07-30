package com.ashish.poc.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ashish.poc.dao.RoleDaoImpl;
import com.ashish.poc.dao.UrlDaoImpl;
import com.ashish.poc.dao.UserDaoImpl;
import com.ashish.poc.model.Role;
import com.ashish.poc.model.Url;
import com.ashish.poc.model.UserDataModel;
import com.ashish.poc.model.Users;

@Component
public class UrlServices {
	
	private static final Logger log = Logger.getLogger(UrlServices.class);
	
	@Autowired
	private UrlDaoImpl urlDaoImpl;
	
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	
	@Transactional//(propagation=Propagation.REQUIRED,readOnly=false, rollbackFor=Exception.class)
	public void createOrUpdateUrl(UserDataModel udm) throws Exception{
		if(udm.getUrls() == null ||udm.getUrls().size() == 0) {
			udm.setErrorMsg("Input to create/update URL is incorrect");
			return;
		}
		Url url = udm.getUrls().get(0);
		if(url.getUrlId() == 0) {
			Url u = urlDaoImpl.findByUrl(url.getUrl().trim());
			
			if(u == null) {
				log.debug("New URL creation request");
				urlDaoImpl.createUrl(url);
			} else {
				udm.setErrorMsg("URL already exists. Please try different URL");
			}
		} else {
			log.debug("Check if URL already exists");
			Url u = urlDaoImpl.findByUrlId(url.getUrlId());
			if(u != null) {
				log.debug("URL already exists. Updating the URL");
				urlDaoImpl.updateUrl(url);
			}
		}
		getUrl(udm);
	}
	
	@Transactional
	public void deleteUrl(UserDataModel udm) throws Exception{
		if(udm.getUrls() == null ||udm.getUrls().size() == 0) {
			udm.setErrorMsg("Input to delete URL is incorrect");
			return;
		}
		Url url = udm.getUrls().get(0);
		if(url.getUrlId() > 0) {
			Url u = urlDaoImpl.findByUrlId(url.getUrlId());
			
			if(u == null) {
				log.debug("URL you want to delete does not exists.");
				udm.setErrorMsg("URL you want to delete does not exists.");
			} else {
				log.debug("Going to delete the URL.");
				urlDaoImpl.deleteUrl(url);
				url.setUrl(null); // As the URL is deleted
			}
		}
		getUrl(udm);
	}
	
	public void getUrl(UserDataModel udm) throws Exception {
		if(udm.getUrls() == null || udm.getUrls().size() == 0) {
			udm.setErrorMsg("Input to retrieve URL is incorrect");
			return;
		}
		
		Url url = udm.getUrls().get(0);
		if(url != null && !StringUtils.isEmpty(url.getUrl())) { // Get URL object by url
			url = urlDaoImpl.findByUrl(url.getUrl().trim());
			if(url == null) {
				log.debug("URL not found");
				udm.setErrorMsg("URL not found");
				return;
			}
			List<Url> urls = new ArrayList<Url>();
			urls.add(url);
			udm.setUrls(urls);
		} else { // Find all URLs
			List<Url> urls = urlDaoImpl.findAll(udm.isGuestUser());
			udm.setUrls(urls);
		}
		
		
		Integer loggedInRoleId = null;
		// Remove username and password if display is not eligible
		if(!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        String name = user.getUsername();
	        
	        if(name != null) {
		        Users u = userDaoImpl.findByUserName(name);
		        
		        if(u!= null && u.getRoleId() != null) {
			        loggedInRoleId = u.getRoleId();
	        	}
	        }
		}

		for(Url tempUrl : udm.getUrls()) {
				
			if(loggedInRoleId == null) { // For anonymous user, if a URL has role id then remove username and password
				if(tempUrl.getRoleId() != null) {
					tempUrl.setUsername("");
					tempUrl.setPassword("");
				}
			} else { // For loggedin user
				if(loggedInRoleId == 1) { // Display username and password for admin users
					break;
				} else if (loggedInRoleId != tempUrl.getRoleId()){ // If role ID matches then display the username and password
					tempUrl.setUsername("");
					tempUrl.setPassword("");
				}
			}
				
				
		} 
		
	}
}
