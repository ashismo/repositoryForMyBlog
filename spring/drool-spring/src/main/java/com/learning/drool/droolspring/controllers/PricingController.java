package com.learning.drool.droolspring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.drool.droolspring.beans.MiscCharge;
import com.learning.drool.droolspring.beans.MiscChargeInput;
import com.learning.drool.droolspring.beans.Quote;
import com.learning.drool.droolspring.beans.QuoteRequest;
import com.learning.drool.droolspring.beans.QuoteResponse;

@RestController
public class PricingController {
	
	@Autowired
	private KieSession session;
	
	@PostMapping(path = "/offers")
	private QuoteResponse getOffers(@RequestBody QuoteRequest quoteRequest) {
		QuoteResponse quoteResponse = new QuoteResponse();
		List<Quote> quotes = new ArrayList<>();
		quoteResponse.setQuotes(quotes);
		
		Quote q = new Quote();
		quotes.add(q);
		q.setFltNum("123");
		q.setProduct("Standard");
		q.setQuoteId("1");
		q.setWeightCharges(100);
		
		MiscChargeInput miscCharges = new MiscChargeInput();
		miscCharges.setQuoteRequest(quoteRequest);
		miscCharges.setQuoteResponse(quoteResponse);
		
		session.insert(miscCharges);
		session.fireAllRules();
		
		return quoteResponse;
	}
}
