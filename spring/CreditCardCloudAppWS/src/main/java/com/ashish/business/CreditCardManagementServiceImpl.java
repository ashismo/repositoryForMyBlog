package com.ashish.business;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class CreditCardManagementServiceImpl {
	@Autowired
	private CreditCardManagementTransactionImpl cardManagementTransactionImpl;
	
	@HystrixCommand(fallbackMethod = "circuitBreakerFallbackMethod", 
			commandProperties = {
	            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
	            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
			},
			ignoreExceptions={Exception.class})
	public String testCircuitBreaker() {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = URI.create("http://debit.mybluemix.net/rest/balanceTransfer?toAccount=654321");
		String output = null;
		try {
			output = restTemplate.getForObject(uri, String.class);
		} catch (Exception e) {
			System.out.println("Exception occured");
			e.printStackTrace();
			throw e;
		}
	    return output;
	}
	
	@HystrixCommand(fallbackMethod = "circuitBreakerFallbackMethod", 
			commandProperties = {
	            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"),
	            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
	            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
			},
			ignoreExceptions={Exception.class})
	public String circuitBreakerFallBack() {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = URI.create("http://debit.mybluemix.net/rest/balanceTransfer?toAccount=654321");
		String output = null;
		try {
			output = restTemplate.getForObject(uri, String.class);
		} catch (Exception e) {
			System.out.println("Exception occured");
			e.printStackTrace();
			throw e;
		}
	    return output;
	}
	
	public String circuitBreakerFallbackMethod() {
		
	    return "Service is temporary down";
	}
}
