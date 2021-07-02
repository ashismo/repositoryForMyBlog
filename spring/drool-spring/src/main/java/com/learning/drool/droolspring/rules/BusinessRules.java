package com.learning.drool.droolspring.rules;

import com.learning.drool.droolspring.beans.QuoteRequest;

public class BusinessRules {
	public boolean isICE(QuoteRequest quoteRequest) {
		if(quoteRequest.getSplHndlCodes() != null && quoteRequest.getSplHndlCodes().contains("ICE")) {
			return true;
		}
		return false;
	}
}
