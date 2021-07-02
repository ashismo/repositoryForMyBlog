package com.learning.drool.droolspring.beans;

import java.util.List;

import lombok.Data;

@Data
public class QuoteResponse {
	private List<Quote> quotes;
}
