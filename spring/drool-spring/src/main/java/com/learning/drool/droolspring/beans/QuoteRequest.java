package com.learning.drool.droolspring.beans;

import java.util.List;

import lombok.Data;

@Data
public class QuoteRequest {
	private String origin;
	private String dest;
	private double weight;
	private List<String> splHndlCodes;
}
