package com.monojit.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.monojit.beans.TestBean;

@RestController
@RequestMapping("/rest")
public class RestCtrl {
	@RequestMapping(value="/student/{name}", method = RequestMethod.GET)
	public TestBean getRestName(@PathVariable String name) {
		TestBean tb = new TestBean();
		tb.setName(name);
		return tb;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public TestBean postRestName(@RequestBody TestBean tb) {
		tb.setName("NewName");
		return tb;
	}
}
