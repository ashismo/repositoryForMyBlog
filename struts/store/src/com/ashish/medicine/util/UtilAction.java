package com.ashish.medicine.util;

import java.util.ArrayList;
import java.util.List;

import com.ashish.medicine.common.bean.States;
import com.opensymphony.xwork2.ActionSupport;

public class UtilAction extends ActionSupport {
	
	private List<States> stateList = new ArrayList<States>();
	public String getStates() {
		String allStates = getText("allStates");
		String states[] = allStates.split(",");
		int count = 1;
		for(String state: states) {
			String code = state.split("-")[0];
			state = state.split("-")[1];
			States s = new States();
			s.setId(count);
			s.setCode(code);
			s.setName(state);
			count++;
			stateList.add(s);
		}
		return ActionSupport.SUCCESS;
//		return allStates;
	}
	public List<States> getStateList() {
		return stateList;
	}
	public void setStateList(List<States> stateList) {
		this.stateList = stateList;
	}
}
