package com.ashish.learning.v4.inheritance;

import org.springframework.stereotype.Component;

public class States {
	private String stateName;

	public States(String stateName) {
		this.stateName = stateName;
	}
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	@Override
	public String toString() {
		return "States [stateName=" + stateName + "]";
	}
}
