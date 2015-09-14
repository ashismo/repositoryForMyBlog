package com.ashish.medicine.util;

import java.util.ArrayList;
import java.util.List;

import com.ashish.medicine.common.bean.States;

public class UtilServiceImpl implements UtilService {
	public List<States> getStates() {
		List<States> stateList = new ArrayList<States>();
		String allStates = new UtilAction().getStates();
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
		
		return stateList;
	}
	
	public boolean isNumber(String number) {
		boolean isNum = false;
		try {
			Integer.parseInt(number);
			isNum = true;
		} catch (NumberFormatException e) {
			isNum = false;
		} catch (Exception e) {
			isNum = false;
		}
		return isNum;
	}
}
