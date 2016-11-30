package com.ashish.poc.ws;

import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.impl.HttpHeadersImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import com.ashish.poc.model.UserDataModel;
import com.ashish.poc.services.UrlServices;
import com.ashish.poc.services.UserServices;
import com.ashish.poc.util.CommonUtil;

@Path("/guide")
@Controller
public class UserGuideWSImpl {
	
	private static final Logger log = Logger.getLogger(UserGuideWSImpl.class);

	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private UrlServices urlService;

	@GET
	@Path("/getHtmlData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_GENERAL')")
	public UserDataModel getHtmlData(@Context HttpHeadersImpl headers,
			@QueryParam("criteria") String criteria) throws Exception {
		UserDataModel userDataModel = new UserDataModel();
		try {
//			Users u = new Users();
//			u.setEmail("aa@bb.com");
//			u.setUserId(6);
//			u.setName("Cognizant1");
//			//userDaoImpl.addAUser(u);
//
//			System.out.println(userDaoImpl.findAll().size());
//			userDataModel.setUsers(null);
//			commonUtil.backupCurrentDatabaseState();
		} catch (Exception e) {
			System.out.println("Failed.....");
			e.printStackTrace();
		}
		
		
		//return Response.status(200).entity(userDaoImpl.findAll()).build();
		return userDataModel;
	}

	@POST
	@Path("/admin/createUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel createUser(UserDataModel userDataModel)
			throws Exception {
		try {
			userServices.createOrUpdateUser(userDataModel);
		} catch (Exception e) {
			log.error("Unable to create/update user", e);
			userDataModel.setErrorMsg("Unable to create/update user");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/admin/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_GENERAL')")
	@WebMethod
	public UserDataModel getUsers(UserDataModel userDataModel)
			throws Exception {
		try {
			userServices.getUsers(userDataModel);
		} catch (Exception e) {
			log.error("Unable to retrieve user information", e);
			userDataModel.setErrorMsg("Unable to retrieve user information");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/createUrl")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel createUrl(UserDataModel userDataModel)
			throws Exception {
		try {
			urlService.createOrUpdateUrl(userDataModel);
		} catch (Exception e) {
			log.error("Unable to create/update URL", e);
			userDataModel.setErrorMsg("Unable to create/update URL");
		}
		return userDataModel;
	}
	
	@POST
	@Path("/getUrl")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel getUrl(UserDataModel userDataModel)
			throws Exception {
		try {
			urlService.getUrl(userDataModel);
		} catch (Exception e) {
			log.error("Unable to retrieve URL information", e);
			userDataModel.setErrorMsg("Unable to retrieve URL information");
		}
		return userDataModel;
	}

}
