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
import org.springframework.stereotype.Controller;

import com.ashish.poc.model.UserDataModel;
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

	@GET
	@Path("/getHtmlData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
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
	@Path("/createUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod
	public UserDataModel createUser(UserDataModel userDataModel)
			throws Exception {
		log.debug("Starting the service");
		userServices.createOrUpdateUser(userDataModel);
		return userDataModel;
	}

}
