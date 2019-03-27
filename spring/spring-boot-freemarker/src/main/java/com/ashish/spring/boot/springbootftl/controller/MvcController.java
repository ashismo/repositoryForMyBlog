package com.ashish.spring.boot.springbootftl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ashish.spring.boot.springbootftl.dao.Employee;

@Controller
public class MvcController {
	@GetMapping("/greet")
    public String greet(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "helloworld";
    }
	
	@GetMapping("/greetWithRest")
    public String greetWithRest(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        
        final String uri = "http://localhost:8080/rest/getEmployee";
        
        RestTemplate restTemplate = new RestTemplate();
        Employee result = restTemplate.getForObject(uri, Employee.class);
         
        System.out.println(result);
        model.addAttribute("employee", result);
        return "helloworldRest";
    }
}
