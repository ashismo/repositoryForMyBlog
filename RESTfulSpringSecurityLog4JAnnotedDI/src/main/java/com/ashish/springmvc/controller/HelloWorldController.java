package com.ashish.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// Below annotation is used to mark this class as controller.
// <context:component-scan base-package="com.ashish.springmvc.controller" /> is declared
// in mvc-applicationContext.xml file to scan controller in com.ashish.springmvc.controller package
@Controller
public class HelloWorldController {
	String message = "Welcome to Spring MVC!";
 
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller: hello");
 
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/getAngularjsLandingPage")
	public ModelAndView getAngularjsLandingPage() {
		System.out.println("in controller: getAngularjsLandingPage");
 
		ModelAndView mv = new ModelAndView("../../restViews/html5AngularJsView");
		mv.addObject("message", message);
		return mv;
	}
	
	@RequestMapping("/homepage")
	public ModelAndView homePage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller: homepage");
 
		ModelAndView mv = new ModelAndView("../../index");
		return mv;
	}
}