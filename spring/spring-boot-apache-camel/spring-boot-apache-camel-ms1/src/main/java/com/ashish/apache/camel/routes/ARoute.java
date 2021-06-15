package com.ashish.apache.camel.routes;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ARoute extends RouteBuilder {

	@Autowired
	private GetCurrentTimeBean getCurrentTimeBean;
	@Override
	public void configure() throws Exception {
		// timer to trigger
		// transformation
		// write into logfile
		
		// [timer://a-timer] a-timer                                  : Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
		from("timer:a-timer")
		.log("${body}")
		.transform().constant("My constant message")
		.log("${body}")
		//.bean("getCurrentTimeBean")
		.bean(getCurrentTimeBean, "getCurrentTime")
		.log("${body}")
		.to("log:a-timer");
		
	}

}


@Component
class GetCurrentTimeBean {
	public String getCurrentTime() {
		return "Time now: " + LocalDateTime.now();
	}
	
	public String getCurrentTime1() {
		return "Time now: " + LocalDateTime.now();
	}
}