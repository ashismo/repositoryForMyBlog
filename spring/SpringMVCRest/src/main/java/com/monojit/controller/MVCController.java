package com.monojit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monojit.beans.TestBean;

@Controller
@RequestMapping("/student")
public class MVCController {
	@RequestMapping(value="/controller/{name}", method = RequestMethod.GET)
	public String getStudentName(@PathVariable String name, ModelMap model) {
		model.addAttribute("studentName", name);
		return "list";
	}
	
	@RequestMapping(value="/static", method = RequestMethod.GET)
	public String getStaticpage() {
		return "redirect:/resources/test.html";
	}
	
	@RequestMapping(value="/rest/{name}", method = RequestMethod.GET)
	public @ResponseBody TestBean getStudentRestName(@PathVariable String name) {
		TestBean tb = new TestBean();
		tb.setName(name);
		return tb;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public @ResponseBody TestBean postRestName(@RequestBody TestBean tb) {
		tb.setName("NewName");
		return tb;
	}
}