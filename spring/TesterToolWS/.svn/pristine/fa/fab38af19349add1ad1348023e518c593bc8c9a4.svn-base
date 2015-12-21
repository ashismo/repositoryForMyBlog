package com.org.coop.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.canonical.beans.UIModel;


@RestController
@RequestMapping("/rest")
public class JsonController {
	private static final Logger log = Logger.getLogger(JsonController.class); 
	
	@RequestMapping(value = "/postService", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json",consumes="application/json")
	@ResponseBody
	public /*ResponseEntity<UIModel>*/ UIModel postService(@RequestBody UIModel uiModel) {
		log.debug("ROLE: ");
		return uiModel;
//		return new ResponseEntity<UIModel>(uiModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getService", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UIModel getService(@RequestParam(value = "id",required = false,
            defaultValue = "0") Integer id) {
		log.debug("getService: ");
		return new UIModel();
	}

}
