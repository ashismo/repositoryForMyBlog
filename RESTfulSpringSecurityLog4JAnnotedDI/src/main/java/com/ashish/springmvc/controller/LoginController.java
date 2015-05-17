package com.ashish.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ashish.springmvc.service.LoginInterface;
import com.ashish.springmvc.service.LogoutInterface;

//Below annotation is used to mark this class as controller.
//<context:component-scan base-package="com.ashish.springmvc.controller" /> is declared
//in mvc-applicationContext.xml file to scan controller in com.ashish.springmvc.controller package
@Controller
public class LoginController {
	private static final Log log = LogFactory.getLog(LoginController.class);
	
	private LoginInterface loginService;
	
	private LogoutInterface logoutService;
	
	// Below annotation is responsible for annotation based dependency injection
	@Autowired
	public void setLoginService(LoginInterface loginService) {
		this.loginService = loginService;
	}

	// Below annotation is responsible for annotation based dependency injection
	@Autowired
	public void setLogoutService(LogoutInterface logoutService) {
		this.logoutService = logoutService;
	}



	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelMap model) {
		log.info("login controller got called");
		if(loginService.isLoginSuccess()) {
			log.info("login service called successfully");
		}
		ModelAndView mv = new ModelAndView("login");
		return mv;
    }
 
    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "denied";
    }
 
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
    	log.info("logout controller got called");
		if(logoutService.isLogoutSuccess()) {
			log.info("logout service called successfully");
		}
        return "logout";
    }
}
