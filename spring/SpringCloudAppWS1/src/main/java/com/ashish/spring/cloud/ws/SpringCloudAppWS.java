package com.ashish.spring.cloud.ws;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.beans.CreditCardBean;
import com.ashish.business.CreditCardManagementImpl;


@RestController
@RequestMapping("/rest")
public class SpringCloudAppWS {
	private static final Logger logger = LoggerFactory.getLogger(SpringCloudAppWS.class);
	
	@Autowired
	private CreditCardManagementImpl creditCardManagementImpl;
	/**
	 * This method returns PNR number for a given customer
	 * @return
	 */
	@ApiOperation(value="getPNR", notes="Returns PNR details for a given customer")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pnr", value = "PNR", required = true, dataType = "string", paramType = "query", defaultValue="Ashish")
      })
	@RequestMapping(path="/getPNR", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public String getPNR(@RequestParam(name="pnr")String pnr) {
		logger.info("Test");
		
		CreditCardBean ccBean = new CreditCardBean();
		ccBean.setFirstName("Ashish");
		ccBean.setLastName("Mondal");
		ccBean.setUserName("ashismo");
		ccBean.setPaid(100);
		ccBean.setOutstanding(200);
		
		creditCardManagementImpl.addCrCardCustomerData(ccBean);
		
		
        return "Valid PNR: " + pnr;
    }

}
