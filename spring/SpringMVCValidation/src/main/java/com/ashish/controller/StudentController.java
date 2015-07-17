package com.ashish.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ashish.beans.Student;

@Controller
public class StudentController {

	/**
	 * The request mapping will work for two different URLs
	 * @param mm
	 * @return
	 */
	@RequestMapping({"/*", "/student"})
	public String student(ModelMap mm) {
		// Bind Student object with model attribute called "student" (refer student.jsp file)
		mm.addAttribute("student", new Student());
		return "student";
	}

	/**
	 * This method validates the user input and displays the right jsp to the user.
	 * Note that BindingResult must follow @ModelAttribute
	 * In this method Http request handling also shown (HttpServletRequest has been used as method parameter)
	 * @param student
	 * @param errors
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public ModelAndView addStudent(@Valid @ModelAttribute("student") Student student, BindingResult errors, ModelMap model, HttpServletRequest req) {
		
		// If the form has error then it will render user the input screen
		if (errors.hasErrors()) {
			return new ModelAndView("student", "student", model);
		}
		
		// Populating model from http request and Student object
		model.addAttribute("name", req.getParameter("name"));
		model.addAttribute("age", student.getAge());
		model.addAttribute("id", student.getId());
		return new ModelAndView("result", "student", model);
	}
}
