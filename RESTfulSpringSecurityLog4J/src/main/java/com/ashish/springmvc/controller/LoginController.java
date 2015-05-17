package com.ashish.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//Below annotation is used to mark this class as controller.
//<context:component-scan base-package="com.ashish.springmvc.controller" /> is declared
//in mvc-applicationContext.xml file to scan controller in com.ashish.springmvc.controller package
@Controller
public class LoginController {
	private static final Log log = LogFactory.getLog(LoginController.class);
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//    public String defaultPage(ModelMap map) {
//		log.info("Going to redirect to /defaultPage URL");
//        return "redirect:/defaultPage";
//    }
//	
//	@RequestMapping(value = "/defaultPage", method = RequestMethod.GET)
//    public String displayDefaultPage(ModelMap map) {
//		log.info("Going to load index.jsp page");
//        return "../../index";
//    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelMap model) {
		log.info("Going login page got called");
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
        return "logout";
    }
}
