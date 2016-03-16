package com.ashish.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.canonical.SearchBean;
import com.ashish.exception.CustomException;


@RestController
@RequestMapping("/rest")
public class SpringBootAppWS {
	private static final Logger logger = LoggerFactory.getLogger(SpringBootAppWS.class);
	
	@RequestMapping(path="/getPNR", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
    public SearchBean getPNR() {
		logger.info("Test");
		SearchBean sb = new SearchBean();
		sb.setPnr("PNR");
        return sb;
    }

	@RequestMapping(path="/getAdv", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
    public SearchBean getAdv() {
		SearchBean sb = new SearchBean();
		sb.setAdvNo("Adv#");
        return sb;
    }
	
	@RequestMapping(path="/getDefaultException", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
    public String getDefaultException() {
        String name = null;
        System.out.println(name.toLowerCase());   // Null pointer exception will be thrown from here
		return null;
    }
	
	@RequestMapping(path="/getCustomException", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
    public String getCustomException() throws CustomException {
		System.out.println("Testing Custom Exception"); 
		if(true) {
        	throw new CustomException("Custom Exception");
        }
		return null;
    }
}
