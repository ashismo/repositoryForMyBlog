package com.ashish.spring.cloud.ws;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.beans.CreditCardBean;
import com.ashish.business.CreditCardManagementServiceImpl;
import com.ashish.business.CreditCardManagementTransactionImpl;

@EnableCircuitBreaker
//@EnableHystrix
//@EnableAutoConfiguration
@RestController
@RequestMapping("/rest")
public class SpringCloudAppWS {
	private static final Logger logger = LoggerFactory.getLogger(SpringCloudAppWS.class);
	
	@Autowired
	private CreditCardManagementTransactionImpl creditCardManagementImpl;
	
	@Autowired
	private CreditCardManagementServiceImpl cardManagementServiceImpl;
	
	/**
	 * Make credit card bill payment
	 * @return
	 */
	@ApiOperation(value="payCreditCardBill", notes="credit card bill payment")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "creditCardNo", value = "12345678", required = true, dataType = "string", paramType = "query", defaultValue="12345678")
      })
	@RequestMapping(path="/payCreditCardBill", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public String payCreditCardBill(@RequestParam(name="creditCardNo")String creditCardNo) {
		logger.info("Test");
		
		CreditCardBean ccBean = new CreditCardBean();
		ccBean.setFirstName("Ashish");
		ccBean.setLastName("Mondal");
		ccBean.setUserName("ashismo");
		ccBean.setPaid(100);
		ccBean.setOutstanding(200);
		
		creditCardManagementImpl.addCrCardCustomerData(ccBean);
		
		
        return "Rs.100/- has been paid to credit card: " + creditCardNo;
    }
	
	@ApiOperation(value="circuitBreaker", notes="Test circuit breaker")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "circuitBreaker")
      })
	@RequestMapping(path="/circuitBreaker", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public String testCircuitBreaker() {
		logger.info("circuitBreaker testing");
		
		long start = System.currentTimeMillis();
		
		String output = cardManagementServiceImpl.testCircuitBreaker();
		
		long end = System.currentTimeMillis();
		
		System.out.println("circuitBreaker Execution time = " + (end - start));
		return output;
    }
	
	@ApiOperation(value="circuitBreakerFallBack", notes="Test circuit breaker")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "circuitBreakerFallBack")
      })
	@RequestMapping(path="/circuitBreakerFallBack", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public String circuitBreakerFallBack() {
		logger.info("circuitBreakerFallBack testing");
		
		long start = System.currentTimeMillis();
		String output = cardManagementServiceImpl.circuitBreakerFallBack();
		long end = System.currentTimeMillis();
		
		System.out.println("circuitBreakerFallBack Execution time = " + (end - start));
		return output;
    }
}
