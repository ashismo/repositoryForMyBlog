package com.ashish.poc.ws;

import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ashish.poc.model.UserDataModel;
import com.ashish.poc.services.EnvironmentServices;
import com.ashish.poc.services.ModuleServices;
import com.ashish.poc.services.RoleServices;
import com.ashish.poc.util.CommonUtil;

@Path("/master")
@Controller
public class MasterDataWSImpl {
	
	private static final Logger log = Logger.getLogger(MasterDataWSImpl.class);

	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private RoleServices roleServices;
	
	@Autowired
	private EnvironmentServices envServices;
	
	@Autowired
	private ModuleServices moduleServices;


	@POST
	@Path("/createRole")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel createRole(UserDataModel userDataModel)
			throws Exception {
		try {
			roleServices.createOrUpdateRole(userDataModel);
		} catch (Exception e) {
			log.error("Unable to create/update role", e);
			userDataModel.setErrorMsg("Unable to create/update role");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/getRole")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel getRoles(UserDataModel userDataModel)
			throws Exception {
		try {
			roleServices.getRoles(userDataModel);
		} catch (Exception e) {
			log.error("Unable to retrieve role data", e);
			userDataModel.setErrorMsg("Unable to retrieve role data");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/createEnv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel createEnv(UserDataModel userDataModel)
			throws Exception {
		
		try {
			envServices.createOrUpdateEnvironments(userDataModel);
		} catch (Exception e) {
			log.error("Unable to create/update environment", e);
			userDataModel.setErrorMsg("Unable to create/update environment");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/getEnv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel getEnvironments(UserDataModel userDataModel)
			throws Exception {
		try {
			envServices.getEnvironments(userDataModel);
		} catch (Exception e) {
			log.error("Unable to retrieve environment data", e);
			userDataModel.setErrorMsg("Unable to retrieve environment data");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/createModule")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel createModule(UserDataModel userDataModel)
			throws Exception {
		
		try {
			moduleServices.createOrUpdateModules(userDataModel);
		} catch (Exception e) {
			log.error("Unable to create/update module", e);
			userDataModel.setErrorMsg("Unable to create/update module");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/getModule")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel getModule(UserDataModel userDataModel)
			throws Exception {
		try {
			moduleServices.getModules(userDataModel);
		} catch (Exception e) {
			log.error("Unable to retrieve module data", e);
			userDataModel.setErrorMsg("Unable to retrieve module data");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/attachModule")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel attachModule(UserDataModel userDataModel)
			throws Exception {
		
		try {
			moduleServices.attachOrDetachModuleAndEnv(userDataModel);
		} catch (Exception e) {
			log.error("Unable to attache/detach module with environment", e);
			userDataModel.setErrorMsg("Unable to attache/detach module with environment");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/getModuleEnv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel getModuleEnv(UserDataModel userDataModel)
			throws Exception {
		try {
			moduleServices.getModuleEnvironment(userDataModel);
		} catch (Exception e) {
			log.error("Unable to retrieve module env data", e);
			userDataModel.setErrorMsg("Unable to retrieve module env data");
		}
		return userDataModel;
	}

}
