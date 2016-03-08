package com.org.coop.admin.ws;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.org.coop.canonical.master.beans.MasterDataBean;
import com.org.coop.canonical.master.beans.ModuleMasterBean;

@RestController
@RequestMapping("/rest")
public class MasterDataSetupSecurityWSImpl extends BasicSecurityWSImpl {
	
	private static final Logger log = Logger.getLogger(MasterDataSetupSecurityWSImpl.class); 
	
	@PreAuthorize("hasRole('ROLE_ADMIN_CREATE')")
	@RequestMapping(value = "/saveCountryStateDist", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json",consumes="application/json")
	public MasterDataBean saveCountryStateDist(@RequestBody MasterDataBean masterDataBean) {
		try {
			String url = env.getProperty("adminSvc.application.url") + "/saveCountryStateDist";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MasterDataBean> entity = new HttpEntity<MasterDataBean>(masterDataBean, headers);
			ResponseEntity<MasterDataBean> responseObj = restTemplate.exchange(url, HttpMethod.POST, entity, MasterDataBean.class);
			log.debug(responseObj.getBody());
		} catch (Exception e) {
			log.error("Error Creating/Updating country/state/district", e);
			masterDataBean.setErrorMsg("Error Creating/Updating country/state/district");
		}
		return masterDataBean;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_READ')")
	@RequestMapping(value = "/getCountryStateDist", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public MasterDataBean getCountryStateDist(@RequestParam(value = "countryCode",required = true,defaultValue = "") String countryCode,
			@RequestParam(value = "stateCode",required = true,defaultValue = "") String stateCode,
			@RequestParam(value = "distCode",required = true,defaultValue = "") String distCode) {
		MasterDataBean masterData = new MasterDataBean();
		try {
			String url = env.getProperty("adminSvc.application.url") + "/getCountryStateDist?countryCode="+countryCode + 
							"&stateCode=" + stateCode + "&distCode=" + distCode;
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<MasterDataBean> responseObj = restTemplate.exchange(url, HttpMethod.GET, getHttpEntities(), MasterDataBean.class);
			masterData = responseObj.getBody();
			log.debug(responseObj);
		} catch (Exception e) {
			log.error("Error Retrieving Country by Country Id", e);
			masterData.setErrorMsg("Error Retrieving Country by Country Id: " + countryCode);
		}
		return masterData;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_CREATE')")
	@RequestMapping(value = "/deleteCountryStateDist", method = RequestMethod.DELETE, headers="Accept=application/json",produces="application/json",consumes="application/json")
	public MasterDataBean deleteCountryStateDist(@RequestBody MasterDataBean masterDataBean,
			@RequestParam(value = "countryCode",required = true,defaultValue = "") String countryCode,
			@RequestParam(value = "stateCode",required = true,defaultValue = "") String stateCode,
			@RequestParam(value = "distCode",required = true,defaultValue = "") String distCode) {
		try {
			String url = env.getProperty("adminSvc.application.url") + "/deleteCountryStateDist?countryCode=" + countryCode + "&stateCode=" + stateCode + "&distCode=" + distCode;
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MasterDataBean> entity = new HttpEntity<MasterDataBean>(masterDataBean, headers);
			ResponseEntity<MasterDataBean> responseObj = restTemplate.exchange(url, HttpMethod.DELETE, entity, MasterDataBean.class);
			log.debug(responseObj.getBody());
			
		} catch (Exception e) {
			log.error("Error while deleting country/state/district", e);
			masterDataBean.setErrorMsg("Error while deleting country/state/district");
		}
		return masterDataBean;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_READ')")
	@RequestMapping(value = "/getModuleRulesAndPermissions", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public MasterDataBean getModuleRulesAndPermissions(@RequestParam(value = "moduleName",required = true,defaultValue = "") String moduleName) {
		MasterDataBean masterData = new MasterDataBean();
		try {
			String url = env.getProperty("adminSvc.application.url") + "/getModuleRulesAndPermissions?moduleName="+moduleName;
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<MasterDataBean> responseObj = restTemplate.exchange(url, HttpMethod.GET, getHttpEntities(), MasterDataBean.class);
			masterData = responseObj.getBody();
			log.debug(responseObj);
		} catch (Exception e) {
			log.error("Error Retrieving Module details for module Name", e);
			masterData.setErrorMsg("Error Retrieving Module details for module Name: " + moduleName);
		}
		return masterData;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_CREATE')")
	@RequestMapping(value = "/saveModuleRulesAndPermissions", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public MasterDataBean saveModuleRulesAndPermissions(@RequestBody MasterDataBean masterDataBean) {
		MasterDataBean masterData = new MasterDataBean();
		try {
			String url = env.getProperty("adminSvc.application.url") + "/saveModuleRulesAndPermissions";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MasterDataBean> entity = new HttpEntity<MasterDataBean>(masterDataBean, headers);
			ResponseEntity<MasterDataBean> responseObj = restTemplate.exchange(url, HttpMethod.POST, entity, MasterDataBean.class);
			log.debug(responseObj.getBody());
		} catch (Exception e) {
			log.error("Error Retrieving Module details for module:" + ((ModuleMasterBean)masterDataBean.getModules().toArray()[0]).getModuleName() , e);
			masterData.setErrorMsg("Error saving Module details for module Name: " + ((ModuleMasterBean)masterDataBean.getModules().toArray()[0]).getModuleName());
		}
		return masterData;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_READ')")
	@RequestMapping(value = "/getSecurityQuestions", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public MasterDataBean getSecurityQuestions(@RequestParam(value = "noOfSecurityQuestion",required = true,defaultValue = "0") int noOfSecurityQuestion) {
		MasterDataBean masterData = new MasterDataBean();
		try {
			String url = env.getProperty("adminSvc.application.url") + "/getSecurityQuestions?noOfSecurityQuestion="+noOfSecurityQuestion;
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<MasterDataBean> responseObj = restTemplate.exchange(url, HttpMethod.GET, getHttpEntities(), MasterDataBean.class);
			masterData = responseObj.getBody();
			log.debug(responseObj);
		} catch (Exception e) {
			log.error("Error Retrieving Security questions:" , e);
			masterData.setErrorMsg("Error Retrieving the Security questions");
		}
		return masterData;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_CREATE')")
	@RequestMapping(value = "/saveSecurityQuestions", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public MasterDataBean saveSecurityQuestions(@RequestBody MasterDataBean masterDataBean) {
		MasterDataBean masterData = new MasterDataBean();
		try {
			String url = env.getProperty("adminSvc.application.url") + "/saveSecurityQuestions";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MasterDataBean> entity = new HttpEntity<MasterDataBean>(masterDataBean, headers);
			ResponseEntity<MasterDataBean> responseObj = restTemplate.exchange(url, HttpMethod.POST, entity, MasterDataBean.class);
			log.debug(responseObj.getBody());
		} catch (Exception e) {
			log.error("Error while saving Security questions" , e);
			masterData.setErrorMsg("Error while saving Security questions ");
		}
		return masterData;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN_CREATE')")
	@RequestMapping(value = "/deleteSecurityQuestions", method = RequestMethod.DELETE, headers="Accept=application/json",produces="application/json")
	public MasterDataBean deleteSecurityQuestions(@RequestBody MasterDataBean masterDataBean) {
		MasterDataBean masterData = new MasterDataBean();
		try {
			String url = env.getProperty("adminSvc.application.url") + "/deleteSecurityQuestions";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MasterDataBean> entity = new HttpEntity<MasterDataBean>(masterDataBean, headers);
			ResponseEntity<MasterDataBean> responseObj = restTemplate.exchange(url, HttpMethod.DELETE, entity, MasterDataBean.class);
			log.debug(responseObj.getBody());
		} catch (Exception e) {
			log.error("Error while deleting Security questions" , e);
			masterData.setErrorMsg("Error while deleting Security questions ");
		}
		return masterData;
	}
	
}
