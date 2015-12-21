package com.org.coop.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.coop.canonical.beans.UIModel;


@Path("/rest1")
public class JerseyController {
	private static final Logger log = Logger.getLogger(JerseyController.class); 
	
	@POST
	@Path("/postService")
	@Produces(MediaType.APPLICATION_JSON)
	public /*ResponseEntity<UIModel>*/ UIModel postService(@RequestBody UIModel uiModel) {
		log.debug("ROLE: ");
		return uiModel;
//		return new ResponseEntity<UIModel>(uiModel, HttpStatus.OK);
	}
	
	@GET
	@Path("/getService")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody UIModel getService(@RequestParam(value = "id",required = false,
            defaultValue = "0") Integer id) {
		log.debug("getService: ");
		return new UIModel();
	}

}
