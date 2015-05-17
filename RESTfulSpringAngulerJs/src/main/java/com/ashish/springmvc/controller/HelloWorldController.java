package com.ashish.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
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
}