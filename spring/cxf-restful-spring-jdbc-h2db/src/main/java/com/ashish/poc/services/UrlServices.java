package com.ashish.poc.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ashish.poc.dao.UrlDaoImpl;
import com.ashish.poc.dao.UserDaoImpl;
import com.ashish.poc.model.Url;
import com.ashish.poc.model.UserDataModel;
import com.ashish.poc.model.Users;

@Component
public class UrlServices {
	
	private static final Logger log = Logger.getLogger(UrlServices.class);
	
	@Autowired
	private UrlDaoImpl urlDaoImpl;
	
	@Transactional//(propagation=Propagation.REQUIRED,readOnly=false, rollbackFor=Exception.class)
	public void createOrUpdateUrl(UserDataModel udm) throws Exception{
		if(udm.getUrls() == null ||udm.getUrls().size() == 0) {
			udm.setErrorMsg("Input to create/update URL is incorrect");
			return;
		}
		Url url = udm.getUrls().get(0);
		if(url.getUrlId() == 0) {
			Url u = urlDaoImpl.findByUrl(url.getUrl());
			
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
	
	public void getUrl(UserDataModel udm) throws Exception {
		if(udm.getUrls() == null || udm.getUrls().size() == 0) {
			udm.setErrorMsg("Input to retrieve URL is incorrect");
			return;
		}
		
		Url url = udm.getUrls().get(0);
		if(url != null && !StringUtils.isEmpty(url.getUrl())) { // Get user by urlid
			url = urlDaoImpl.findByUrl(url.getUrl());
			if(url == null) {
				log.debug("URL not found");
				udm.setErrorMsg("URL not found");
				return;
			}
			List<Url> urls = new ArrayList<Url>();
			urls.add(url);
			udm.setUrls(urls);
		} else { // Find all URLs
			List<Url> urls = urlDaoImpl.findAll();
			udm.setUrls(urls);
		}
		
	}
}
