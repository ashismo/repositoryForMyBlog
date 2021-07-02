package com.learning.drool.droolspring.beans;

import java.util.List;

import lombok.Data;

@Data
public class Quote {
	private String quoteId;
	private String fltNum;
	private String product;
	private double weightCharges;
	private List<MiscCharge> miscCharges;
}
